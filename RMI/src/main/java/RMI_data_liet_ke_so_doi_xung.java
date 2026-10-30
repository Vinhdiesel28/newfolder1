import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_liet_ke_so_doi_xung {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static List<Integer> solve(Object data) {
        String[] p = data.toString().split(";");
        int n = Integer.parseInt(p[0].trim()), k = Integer.parseInt(p[1].trim());
        List<Integer> r = new ArrayList<>();
        for (int x = n; x < k; x++) {
            String s = Integer.toString(x);
            if (s.equals(new StringBuilder(s).reverse().toString())) r.add(x);
        }
        return r;
    }
}
