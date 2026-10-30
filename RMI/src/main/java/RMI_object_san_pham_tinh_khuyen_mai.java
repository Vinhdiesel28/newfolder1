import RMI.*;
import java.rmi.registry.*;

public class RMI_object_san_pham_tinh_khuyen_mai {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((ProductX) s.requestObject(msv, qCode)));
    }

    public static ProductX solve(ProductX p) {
        int total = 0;
        for (char c : p.getDiscountCode().toCharArray()) if (c >= '0' && c <= '9') total += c - '0';
        p.setDiscount(total);
        return p;
    }
}
