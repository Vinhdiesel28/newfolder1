import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_vi_tri_dinh_cuc_bo {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static List<Integer> solve(Object data) {
        int[] a;
        if (data instanceof int[]) a = (int[]) data;
        else if (data instanceof Integer[]) a = Arrays.stream((Integer[]) data).mapToInt(Integer::intValue).toArray();
        else a = ((List<?>) data).stream().mapToInt(x -> ((Number) x).intValue()).toArray();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
            if ((i == 0 || a[i] > a[i - 1]) && (i == a.length - 1 || a[i] > a[i + 1])) result.add(i + 1);
        return result;
    }
}
