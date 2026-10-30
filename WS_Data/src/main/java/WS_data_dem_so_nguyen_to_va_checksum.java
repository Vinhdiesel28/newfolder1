import vn.medianews.*;
import java.net.URL;
import java.util.*;

public class WS_data_dem_so_nguyen_to_va_checksum {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataString(msv, qCode, solve(s.getData(msv, qCode)));
    }

    public static String solve(List<Integer> a) {
        int primes = 0, checksum = 0;
        for (int i = 0; i < a.size(); i++) {
            int x = a.get(i);
            if (isPrime(x)) primes++;
            checksum = (int) Math.floorMod(checksum + (long) (i + 1) * x, 100000);
        }
        return "primeCount=" + primes + ";checksum=" + checksum;
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int d = 2; (long) d * d <= n; d++) if (n % d == 0) return false;
        return true;
    }
}
