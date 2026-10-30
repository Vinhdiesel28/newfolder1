import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_tong_cac_so_trong_mang {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataInt(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static int solve(List<Integer> a) {
        return a.stream().mapToInt(Integer::intValue).sum();
    }
}
