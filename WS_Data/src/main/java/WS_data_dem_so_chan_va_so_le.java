import vn.medianews.*;
import java.net.URL;
import java.util.*;

public class WS_data_dem_so_chan_va_so_le {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataStringArray(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static List<String> solve(List<Integer> a) {
        int even = 0;
        for (int x : a) if (x % 2 == 0) even++;
        return List.of("EVEN=" + even, "ODD=" + (a.size() - even));
    }
}
