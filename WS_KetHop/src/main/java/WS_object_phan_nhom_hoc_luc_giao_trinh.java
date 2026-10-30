import java.util.*;
import org.w3c.dom.Element;

public class WS_object_phan_nhom_hoc_luc_giao_trinh {
    public static void main(String[] args) throws Exception {
        SoapClient s = new SoapClient(args[2]);
        List<Element> students = s.call("requestObject", args[0], args[1]);
        s.call("submitObject", args[0], args[1], solve(students));
    }

    public static List<String[]> solve(List<Element> a) {
        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < 4; i++) groups.add(new ArrayList<>());
        for (Element student : a) {
            double score = Double.parseDouble(SoapClient.field(student, "score"));
            groups.get(score >= 8 ? 0 : score >= 6.5 ? 1 : score >= 5 ? 2 : 3).add(SoapClient.field(student, "name"));
        }
        List<String[]> r = new ArrayList<>();
        for (List<String> group : groups) r.add(group.toArray(new String[0]));
        return r;
    }
}
