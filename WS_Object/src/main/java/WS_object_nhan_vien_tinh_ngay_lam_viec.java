import vn.medianews.*;
import java.net.URL;
import java.time.*;

public class WS_object_nhan_vien_tinh_ngay_lam_viec {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitEmployee(msv, qCode, solve(s.requestEmployee(msv, qCode)));
    }

    public static Employee solve(Employee e) {
        LocalDate start = WSNgay.ngay(e.getStartDate()), end = WSNgay.ngay(e.getEndDate());
        int days = 0;
        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) if (d.getDayOfWeek().getValue() <= 5) days++;
        e.setWorkingDays(days);
        return e;
    }
}
