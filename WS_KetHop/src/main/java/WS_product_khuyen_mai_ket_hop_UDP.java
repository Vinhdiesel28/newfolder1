import java.net.*;
import java.nio.charset.StandardCharsets;
import org.w3c.dom.Element;

public class WS_product_khuyen_mai_ket_hop_UDP {
    public static void main(String[] args) throws Exception {
        SoapClient s = new SoapClient(args[1]);
        Element product = s.call("getProduct", args[0]).get(0);
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(15000);
            String data = SoapClient.field(product, "id") + ";" + SoapClient.field(product, "code");
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            int port = args.length > 3 ? Integer.parseInt(args[3]) : 2208;
            socket.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName(args[2]), port));
            DatagramPacket packet = new DatagramPacket(new byte[65507], 65507);
            socket.receive(packet);
            String code = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
            SoapClient.set(product, "discount", solve(code));
            s.call("order", product);
        }
    }

    public static int solve(String code) {
        int total = 0;
        for (char c : code.toCharArray()) if (c >= '0' && c <= '9') total += c - '0';
        return total;
    }
}
