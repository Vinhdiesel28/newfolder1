import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_dem_tan_so_xuat_hien {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataStringArray(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static List<String> solve(List<Integer> a) {
        Map<Integer, Integer> f = new TreeMap<>();
        for (int x : a) f.merge(x, 1, Integer::sum);
        List<String> r = new ArrayList<>();
        f.forEach((x, n) -> r.add(x + ", " + n));
        return r;
    }
}
