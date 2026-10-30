import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_chuyen_he_8_va_16 {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataStringArray(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static List<String> solve(List<Integer> a) {
        List<String> r = new ArrayList<>();
        for (int x : a) r.add(Integer.toOctalString(x) + "|" + Integer.toHexString(x).toUpperCase(Locale.ROOT));
        return r;
    }
}
