import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_phan_tu_lon_thu_K {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static Integer solve(Object data) {
        String[] p = data.toString().split(";");
        int[] a = Arrays.stream(p[0].trim().split("\\s*,\\s*")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(a);
        return a[a.length - Integer.parseInt(p[1].trim())];
    }
}
