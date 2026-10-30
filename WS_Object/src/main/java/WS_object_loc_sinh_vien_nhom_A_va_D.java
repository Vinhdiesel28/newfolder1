import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_object_loc_sinh_vien_nhom_A_va_D {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListStudent(msv, qCode, solve(s.requestListStudent(msv, qCode)));
    }

    public static List<Student> solve(List<Student> a) {
        List<Student> r = new ArrayList<>();
        for (Student s : a) if (s.getScore() >= 8 || s.getScore() < 5) r.add(s);
        return r;
    }
}
