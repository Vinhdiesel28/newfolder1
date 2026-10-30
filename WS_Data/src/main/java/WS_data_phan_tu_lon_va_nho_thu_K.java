import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_phan_tu_lon_va_nho_thu_K {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataIntArray(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static List<Integer> solve(List<Integer> input) {
        int k = input.get(0);
        List<Integer> a = new ArrayList<>(input.subList(1, input.size()));
        Collections.sort(a);
        return Arrays.asList(a.get(a.size() - k), a.get(k - 1));
    }
}
