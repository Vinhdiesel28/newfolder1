import vn.medianews.*;
import java.net.URL;
import java.util.*;

public class WS_object_loc_va_sap_xep_khach_hang_VIP {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListCustomer(msv, qCode, solve(s.requestListCustomer(msv, qCode)));
    }

    public static List<Customer> solve(List<Customer> a) {
        List<Customer> r = new ArrayList<>();
        for (Customer c : a) if (c.getPurchaseCount() >= 6 && c.getTotalSpent() >= 4000) r.add(c);
        r.sort(Comparator.comparingDouble((Customer c) -> c.getTotalSpent()).reversed()
                .thenComparing(Customer::getCustomerId));
        return r;
    }
}
