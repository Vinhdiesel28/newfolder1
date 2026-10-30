import RMI.*;
import java.rmi.registry.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class RMI_object_don_hang_tao_ma {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((Order) s.requestObject(msv, qCode)));
    }

    public static Order solve(Order o) {
        String customer = o.getCustomerCode();
        o.setOrderCode(o.getShippingType().substring(0, 2).toUpperCase(Locale.ROOT)
                + customer.substring(customer.length() - 3)
                + LocalDate.parse(o.getOrderDate()).format(DateTimeFormatter.ofPattern("ddMM")));
        return o;
    }
}
