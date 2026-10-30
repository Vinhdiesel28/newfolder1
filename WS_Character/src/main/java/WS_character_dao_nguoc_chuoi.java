import vn.medianews.*;
import java.net.URL;

public class WS_character_dao_nguoc_chuoi {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapCharacterService s = new CharacterService(new URL(args[2])).getSoapCharacterServicePort();
        s.submitString(msv, qCode, solve(s.requestString(msv, qCode)));
    }

    public static String solve(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
