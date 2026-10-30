import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_object_don_hang_khach_hang_tong_tien_cao_nhat {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitListOrder(msv, qCode, solve(s.requestListOrder(msv, qCode)));
    }

    public static List<Order> solve(List<Order> a) {
        Map<String, Double> total = new HashMap<>();
        for (Order o : a) total.merge(o.getCustomerId(), (double) o.getAmount(), Double::sum);
        double max = total.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);
        List<Order> r = new ArrayList<>();
        for (Order o : a) if (Double.compare(total.get(o.getCustomerId()), max) == 0) r.add(o);
        return r;
    }
}
