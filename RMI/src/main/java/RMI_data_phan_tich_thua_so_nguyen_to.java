import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_phan_tich_thua_so_nguyen_to {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static List<Integer> solve(Object data) {
        long n = Long.parseLong(data.toString());
        List<Integer> r = new ArrayList<>();
        for (int p = 2; p <= n / p; p++) while (n % p == 0) { r.add(p); n /= p; }
        if (n > 1) r.add(Math.toIntExact(n));
        return r;
    }
}
