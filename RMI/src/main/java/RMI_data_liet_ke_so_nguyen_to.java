import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_liet_ke_so_nguyen_to {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static List<Integer> solve(Object data) {
        int n = Integer.parseInt(data.toString());
        boolean[] composite = new boolean[n + 1];
        List<Integer> r = new ArrayList<>();
        for (int i = 2; i <= n; i++) if (!composite[i]) {
            r.add(i);
            if ((long) i * i <= n) for (int j = i * i; j <= n; j += i) composite[j] = true;
        }
        return r;
    }
}
