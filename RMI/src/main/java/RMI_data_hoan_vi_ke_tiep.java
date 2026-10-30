import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_hoan_vi_ke_tiep {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static String solve(Object data) {
        int[] a = Arrays.stream(data.toString().trim().split("\\s*,\\s*")).mapToInt(Integer::parseInt).toArray();
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) i--;
        if (i >= 0) {
            int j = a.length - 1;
            while (a[j] <= a[i]) j--;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        for (int l = i + 1, h = a.length - 1; l < h; l++, h--) {
            int t = a[l]; a[l] = a[h]; a[h] = t;
        }
        StringJoiner r = new StringJoiner(",");
        for (int x : a) r.add(Integer.toString(x));
        return r.toString();
    }
}
