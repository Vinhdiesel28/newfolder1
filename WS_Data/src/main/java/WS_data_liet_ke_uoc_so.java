import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_liet_ke_uoc_so {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataIntArray(msv, qCode, solve(s.getDataDouble(msv, qCode)));
    }

    public static List<Integer> solve(double value) {
        int n = (int) Math.abs(value);
        List<Integer> r = new ArrayList<>(), high = new ArrayList<>();
        for (int i = 1; i <= n / i; i++) if (n % i == 0) {
            r.add(i);
            if (i != n / i) high.add(n / i);
        }
        Collections.reverse(high); r.addAll(high); r.add(0, r.size());
        return r;
    }
}
