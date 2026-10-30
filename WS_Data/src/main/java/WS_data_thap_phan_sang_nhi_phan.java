import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_thap_phan_sang_nhi_phan {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataStringArray(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static List<String> solve(List<Integer> a) {
        List<String> r = new ArrayList<>();
        for (int x : a) r.add(Integer.toBinaryString(x));
        return r;
    }
}
