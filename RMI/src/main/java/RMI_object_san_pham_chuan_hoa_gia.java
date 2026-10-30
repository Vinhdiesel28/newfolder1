import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_object_san_pham_chuan_hoa_gia {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((Product) s.requestObject(msv, qCode)));
    }

    public static Product solve(Product p) {
        p.setCode(p.getCode().toUpperCase(Locale.ROOT));
        p.setExportPrice(p.getImportPrice() * 1.2);
        return p;
    }
}
