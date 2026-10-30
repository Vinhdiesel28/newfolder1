import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_object_sap_xep_nhan_vien_theo_tham_nien {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListEmployeeY(msv, qCode, solve(s.requestListEmployeeY(msv, qCode)));
    }

    public static List<EmployeeY> solve(List<EmployeeY> a) {
        List<EmployeeY> r = new ArrayList<>(a);
        r.sort(Comparator.comparing(e -> WSNgay.ngay(e.getStartDate())));
        return r;
    }
}
