import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_phuong_sai_do_lech_chuan {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static String solve(Object data) {
        double[] a = Arrays.stream(data.toString().trim().split("\\s*,\\s*")).mapToDouble(Double::parseDouble).toArray();
        double mean = Arrays.stream(a).average().orElse(0), variance = 0;
        for (double x : a) variance += (x - mean) * (x - mean);
        variance /= a.length;
        return String.format(Locale.US, "%.2f : %.2f", variance, Math.sqrt(variance));
    }
}
