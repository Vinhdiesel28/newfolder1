import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_data_doi_tien {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        DataService s = (DataService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIDataService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static String solve(Object data) {
        int amount = Integer.parseInt(data.toString());
        List<String> coins = new ArrayList<>();
        for (int value : new int[]{10, 5, 2, 1}) while (amount >= value) {
            coins.add(Integer.toString(value)); amount -= value;
        }
        return coins.size() + "; " + String.join(",", coins);
    }
}
