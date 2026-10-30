import vn.medianews.*;
import java.util.*;
import java.net.URL;
import java.time.*;

public class WS_object_loc_don_hang_can_uu_tien {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListOrderY(msv, qCode, solve(s.requestListOrderY(msv, qCode), LocalDate.now()));
    }

    public static List<OrderY> solve(List<OrderY> a, LocalDate today) {
        List<OrderY> r = new ArrayList<>();
        for (OrderY o : a) {
            LocalDate date = WSNgay.ngay(o.getOrderDate());
            if (("pending".equals(o.getStatus()) || "processing".equals(o.getStatus()))
                    && !date.isBefore(today.minusDays(30)) && !date.isAfter(today)) r.add(o);
        }
        return r;
    }
}
