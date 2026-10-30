import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_ghep_thanh_so_lon_nhat {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataString(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static String solve(List<Integer> a) {
        List<String> b = new ArrayList<>();
        for (int x : a) b.add(Integer.toString(x));
        b.sort((x, y) -> (y + x).compareTo(x + y));
        String r = String.join("", b);
        return !r.isEmpty() && r.charAt(0) == '0' ? "0" : r;
    }
}
