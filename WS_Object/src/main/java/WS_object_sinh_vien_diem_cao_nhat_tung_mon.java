import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_object_sinh_vien_diem_cao_nhat_tung_mon {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListStudentY(msv, qCode, solve(s.requestListStudentY(msv, qCode)));
    }

    public static List<StudentY> solve(List<StudentY> a) {
        Map<String, Float> best = new HashMap<>();
        for (StudentY s : a) best.merge(s.getSubject(), s.getScore(), Math::max);
        List<StudentY> r = new ArrayList<>();
        for (StudentY s : a) if (Float.compare(s.getScore(), best.get(s.getSubject())) == 0) r.add(s);
        return r;
    }
}
