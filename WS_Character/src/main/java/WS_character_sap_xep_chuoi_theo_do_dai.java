import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_character_sap_xep_chuoi_theo_do_dai {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapCharacterService s = new CharacterService(new URL(args[2])).getSoapCharacterServicePort();
        s.submitCharacterStringArray(msv, qCode, solve(s.requestStringArray(msv, qCode)));
    }

    public static List<String> solve(List<String> a) {
        List<String> r = new ArrayList<>(a);
        r.sort(Comparator.comparingInt(String::length));
        return r;
    }
}
