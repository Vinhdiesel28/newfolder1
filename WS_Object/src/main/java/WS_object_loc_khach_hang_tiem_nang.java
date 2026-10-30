import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_object_loc_khach_hang_tiem_nang {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListCustomer(msv, qCode, solve(s.requestListCustomer(msv, qCode)));
    }

    public static List<Customer> solve(List<Customer> a) {
        List<Customer> r = new ArrayList<>();
        for (Customer c : a) if (c.getTotalSpent() > 5000 && c.getPurchaseCount() >= 5) r.add(c);
        return r;
    }
}
