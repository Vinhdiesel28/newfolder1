import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_phan_tich_thua_so_nguyen_to {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataStringArray(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static List<String> solve(List<Integer> a) {
        List<String> r = new ArrayList<>();
        for (int value : a) {
            int n = value;
            StringJoiner factors = new StringJoiner(", ");
            for (int p = 2; p <= n / p; p++) while (n % p == 0) { factors.add(Integer.toString(p)); n /= p; }
            if (n > 1) factors.add(Integer.toString(n));
            r.add(factors.toString());
        }
        return r;
    }
}
