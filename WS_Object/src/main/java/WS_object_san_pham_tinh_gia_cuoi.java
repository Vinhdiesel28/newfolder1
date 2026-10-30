import vn.medianews.*;
import java.net.URL;

public class WS_object_san_pham_tinh_gia_cuoi {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapObjectService s = new ObjectService(new URL(args[2])).getSoapObjectServicePort();
        s.submitProductY(msv, qCode, solve(s.requestProductY(msv, qCode)));
    }

    public static ProductY solve(ProductY p) {
        p.setFinalPrice(p.getPrice() * (1 + p.getTaxRate() / 100f) * (1 - p.getDiscount() / 100f));
        return p;
    }
}
