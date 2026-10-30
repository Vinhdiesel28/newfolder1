import vn.medianews.*;
import java.util.*;
import java.net.URL;
import java.time.*;

public class WS_object_loc_du_an_gan_hoan_thanh {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListProject(msv, qCode, solve(s.requestListProject(msv, qCode), LocalDate.now()));
    }

    public static List<Project> solve(List<Project> a, LocalDate today) {
        List<Project> r = new ArrayList<>();
        for (Project p : a) {
            LocalDate date = WSNgay.ngay(p.getDueDate());
            if (p.getCompletionPercentage() >= 80 && !date.isBefore(today) && !date.isAfter(today.plusDays(15))) r.add(p);
        }
        return r;
    }
}
