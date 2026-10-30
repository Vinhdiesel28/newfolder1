import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_liet_ke_so_chinh_phuong {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static List<Integer> solve(Object data) {
        long n = Long.parseLong(data.toString());
        List<Integer> r = new ArrayList<>();
        for (long i = 1; i * i < n; i++) r.add(Math.toIntExact(i * i));
        return r;
    }
}
