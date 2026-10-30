import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_thong_ke_so_thuc_nang_cao {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static String solve(Object data) {
        double[] a = Arrays.stream(data.toString().trim().split("\\s*,\\s*")).mapToDouble(Double::parseDouble).toArray();
        double average = Arrays.stream(a).average().orElse(0), variance = 0;
        for (double x : a) variance += (x - average) * (x - average);
        Arrays.sort(a);
        return String.format(Locale.US, "average=%.2f;stddev=%.2f;p95=%.2f",
                average, Math.sqrt(variance / a.length), a[(int) Math.ceil(a.length * 0.95) - 1]);
    }
}
