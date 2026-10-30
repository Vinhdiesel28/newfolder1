import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_sinh_to_hop {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static ArrayList<ArrayList<Integer>> solve(Object data) {
        String[] p = data.toString().split(";");
        int[] a = Arrays.stream(p[0].trim().split("\\s*,\\s*")).mapToInt(Integer::parseInt).toArray();
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        generate(a, Integer.parseInt(p[1].trim()), 0, new ArrayList<>(), r);
        return r;
    }

    static void generate(int[] a, int k, int start, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> r) {
        if (current.size() == k) { r.add(new ArrayList<>(current)); return; }
        for (int i = start; i <= a.length - (k - current.size()); i++) {
            current.add(a[i]); generate(a, k, i + 1, current, r); current.remove(current.size() - 1);
        }
    }
}
