import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_sap_xep_chan_le_xen_ke {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataIntArray(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static List<Integer> solve(List<Integer> a) {
        List<Integer> even = new ArrayList<>(), odd = new ArrayList<>(), r = new ArrayList<>();
        for (int x : a) (x % 2 == 0 ? even : odd).add(x);
        for (int i = 0; i < Math.max(even.size(), odd.size()); i++) {
            if (i < even.size()) r.add(even.get(i));
            if (i < odd.size()) r.add(odd.get(i));
        }
        return r;
    }
}
