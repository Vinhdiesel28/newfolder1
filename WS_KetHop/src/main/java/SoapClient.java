import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class SoapClient {
    static final String XSD = "http://www.w3.org/2001/XMLSchema";
    static final String SOAP = "http://schemas.xmlsoap.org/soap/envelope/";
    final List<Document> docs = new ArrayList<>();
    final Set<String> loaded = new HashSet<>();
    String endpoint;

    static Document parse(InputStream in) throws Exception {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setNamespaceAware(true);
        f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        return f.newDocumentBuilder().parse(in);
    }

    public SoapClient(String wsdl) throws Exception {
        load(new URL(wsdl));
        for (Document d : docs) {
            NodeList addresses = d.getElementsByTagNameNS("http://schemas.xmlsoap.org/wsdl/soap/", "address");
            if (addresses.getLength() > 0) {
                endpoint = ((Element) addresses.item(0)).getAttribute("location");
                break;
            }
        }
        if (endpoint == null) throw new IllegalArgumentException("WSDL SOAP 1.1 endpoint missing");
    }

    void load(URL url) throws Exception {
        if (!loaded.add(url.toString())) return;
        URLConnection c = url.openConnection();
        c.setConnectTimeout(15000); c.setReadTimeout(15000);
        Document d;
        try (InputStream in = c.getInputStream()) { d = parse(in); }
        docs.add(d);
        for (String tag : new String[]{"import", "include"}) {
            NodeList nodes = d.getElementsByTagNameNS("*", tag);
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element) nodes.item(i);
                String path = e.hasAttribute("schemaLocation") ? e.getAttribute("schemaLocation") : e.getAttribute("location");
                if (!path.isEmpty()) load(new URL(url, path));
            }
        }
    }

    Element definition(String tag, String name, String namespace) {
        for (Document d : docs) {
            NodeList all = d.getElementsByTagNameNS(XSD, tag);
            for (int i = 0; i < all.getLength(); i++) {
                Element e = (Element) all.item(i);
                if (e.getParentNode() instanceof Element parent && "schema".equals(parent.getLocalName())
                        && e.getAttribute("name").equalsIgnoreCase(name)
                        && (namespace == null || namespace.equals(parent.getAttribute("targetNamespace")))) return e;
            }
        }
        return null;
    }

    List<Element> fieldsOf(Element definition) {
        if (definition == null) return List.of();
        NodeList list = definition.getElementsByTagNameNS(XSD, "element");
        List<Element> r = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) r.add((Element) list.item(i));
        return r;
    }

    Element valueType(Element field) {
        if (field == null) return null;
        String type = field.getAttribute("type");
        int colon = type.indexOf(':');
        String namespace = field.lookupNamespaceURI(colon < 0 ? null : type.substring(0, colon));
        return definition("complexType", type.substring(colon + 1), namespace);
    }

    void argument(Document d, Element parent, Element field, String name, Object value) {
        if (value instanceof Iterable<?> list) {
            for (Object item : list) argument(d, parent, field, name, item);
            return;
        }
        Element e = d.createElement(name);
        parent.appendChild(e);
        if (value instanceof Element object) {
            List<Element> fields = fieldsOf(valueType(field));
            if (fields.isEmpty()) {
                for (Node n = object.getFirstChild(); n != null; n = n.getNextSibling()) e.appendChild(d.importNode(n, true));
            } else for (Element f : fields) {
                for (Node n = object.getFirstChild(); n != null; n = n.getNextSibling())
                    if (n instanceof Element child && f.getAttribute("name").equals(child.getLocalName() == null ? child.getTagName() : child.getLocalName()))
                        e.appendChild(d.importNode(child, true));
            }
        } else if (value instanceof Object[] array) {
            List<Element> fields = fieldsOf(valueType(field));
            Element item = fields.isEmpty() ? null : fields.get(0);
            String itemName = item == null ? "item" : item.getAttribute("name");
            for (Object x : array) argument(d, e, item, itemName, x);
        } else {
            e.setTextContent(String.valueOf(value));
        }
    }

    public List<Element> call(String operation, Object... args) throws Exception {
        Element def = definition("element", operation, null);
        if (def == null) throw new IllegalArgumentException("WSDL operation missing: " + operation);
        String namespace = ((Element) def.getParentNode()).getAttribute("targetNamespace");
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element envelope = d.createElementNS(SOAP, "soap:Envelope");
        d.appendChild(envelope);
        Element body = d.createElementNS(SOAP, "soap:Body"); envelope.appendChild(body);
        Element op = d.createElementNS(namespace, "ns:" + def.getAttribute("name")); body.appendChild(op);
        List<Element> fields = fieldsOf(def);
        if (fields.size() != args.length) throw new IllegalArgumentException("WSDL argument count: " + operation);
        for (int i = 0; i < args.length; i++) argument(d, op, fields.get(i), fields.get(i).getAttribute("name"), args[i]);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(d), new StreamResult(bytes));
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setConnectTimeout(15000); connection.setReadTimeout(15000);
        connection.setRequestMethod("POST"); connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        String action = "";
        for (Document wsdl : docs) {
            NodeList operations = wsdl.getElementsByTagNameNS("http://schemas.xmlsoap.org/wsdl/soap/", "operation");
            for (int i = 0; i < operations.getLength(); i++) {
                Element binding = (Element) operations.item(i);
                if (((Element) binding.getParentNode()).getAttribute("name").equalsIgnoreCase(operation)) action = binding.getAttribute("soapAction");
            }
        }
        connection.setRequestProperty("SOAPAction", "\"" + action + "\"");
        try {
            try (OutputStream out = connection.getOutputStream()) { out.write(bytes.toByteArray()); }
            int status = connection.getResponseCode();
            Document response;
            try (InputStream in = status < 400 ? connection.getInputStream() : connection.getErrorStream()) { response = parse(in); }
            if (status >= 400 || response.getElementsByTagNameNS(SOAP, "Fault").getLength() > 0)
                throw new IOException(response.getDocumentElement().getTextContent());
            Element responseBody = (Element) response.getElementsByTagNameNS(SOAP, "Body").item(0);
            List<Element> r = new ArrayList<>();
            Element payload = null;
            for (Node n = responseBody.getFirstChild(); n != null; n = n.getNextSibling()) if (n instanceof Element e) { payload = e; break; }
            if (payload != null) for (Node n = payload.getFirstChild(); n != null; n = n.getNextSibling()) if (n instanceof Element e) r.add(e);
            return r;
        } finally { connection.disconnect(); }
    }

    public static String field(Element object, String name) {
        for (Node n = object.getFirstChild(); n != null; n = n.getNextSibling())
            if (n instanceof Element e && name.equals(e.getLocalName() == null ? e.getTagName() : e.getLocalName())) return e.getTextContent();
        return "";
    }

    public static void set(Element object, String name, Object value) {
        for (Node n = object.getFirstChild(); n != null; n = n.getNextSibling())
            if (n instanceof Element e && name.equals(e.getLocalName() == null ? e.getTagName() : e.getLocalName())) { e.setTextContent(value.toString()); return; }
        Element e = object.getOwnerDocument().createElement(name);
        e.setTextContent(value.toString()); object.appendChild(e);
    }
}
