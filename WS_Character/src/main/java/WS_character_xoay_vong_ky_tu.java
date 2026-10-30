import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_character_xoay_vong_ky_tu {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapCharacterService s = new CharacterService(new URL(args[2])).getSoapCharacterServicePort();
        s.submitCharacterCharArray(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static List<Integer> solve(List<Integer> a) {
        List<Integer> r = new ArrayList<>(a);
        if (!r.isEmpty()) Collections.rotate(r, r.get(0));
        return r;
    }
}
