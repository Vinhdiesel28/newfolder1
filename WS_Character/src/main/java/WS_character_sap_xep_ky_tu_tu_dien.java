import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_character_sap_xep_ky_tu_tu_dien {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapCharacterService s = new CharacterService(new URL(args[2])).getSoapCharacterServicePort();
        s.submitCharacterCharArray(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static <T extends Comparable<? super T>> List<T> solve(List<T> a) {
        List<T> r = new ArrayList<>(a);
        Collections.sort(r);
        return r;
    }
}
