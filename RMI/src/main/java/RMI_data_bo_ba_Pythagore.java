import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_bo_ba_Pythagore {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static List<List<Integer>> solve(Object data) {
        int n = Integer.parseInt(data.toString());
        List<List<Integer>> r = new ArrayList<>();
        for (int a = 1; a < n; a++) for (int b = a + 1; b < n; b++) {
            long square = (long) a * a + (long) b * b;
            int c = (int) Math.sqrt(square);
            if (c <= n && (long) c * c == square) r.add(new ArrayList<>(Arrays.asList(a, b, c)));
        }
        return r;
    }
}
