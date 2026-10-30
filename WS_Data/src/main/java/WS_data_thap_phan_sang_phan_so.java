import vn.medianews.*;
import java.util.*;
import java.net.URL;
import java.math.*;

public class WS_data_thap_phan_sang_phan_so {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapDataService s = new DataService(new URL(args[2])).getSoapDataServicePort();
        s.submitDataIntArray(msv, qCode, solve(s.getDataDouble(msv, qCode)));
    }

    public static List<Integer> solve(double value) {
        BigDecimal x = BigDecimal.valueOf(value).stripTrailingZeros();
        BigInteger numerator = x.unscaledValue(), denominator = BigInteger.TEN.pow(Math.max(0, x.scale()));
        if (x.scale() < 0) numerator = numerator.multiply(BigInteger.TEN.pow(-x.scale()));
        BigInteger gcd = numerator.gcd(denominator);
        return Arrays.asList(numerator.divide(gcd).intValueExact(), denominator.divide(gcd).intValueExact());
    }
}
