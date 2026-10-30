import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_data_loai_bo_so_trung_nhau {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataIntArray(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static List<Integer> solve(List<Integer> a) {
        return new ArrayList<>(new LinkedHashSet<>(a));
    }
}
