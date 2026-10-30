import vn.medianews.*;
import java.util.*;
import java.net.URL;
import java.time.*;

public class WS_object_loc_khach_hang_khong_giao_dich_6_thang {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListCustomerY(msv, qCode, solve(s.requestListCustomerY(msv, qCode), LocalDate.now()));
    }

    public static List<CustomerY> solve(List<CustomerY> a, LocalDate today) {
        List<CustomerY> r = new ArrayList<>();
        for (CustomerY c : a) if (WSNgay.ngay(c.getLastTransactionDate()).isBefore(today.minusMonths(6))) r.add(c);
        return r;
    }
}
