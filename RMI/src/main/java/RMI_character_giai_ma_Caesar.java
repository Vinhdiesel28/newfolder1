import RMI.*;
import java.rmi.registry.*;

public class RMI_character_giai_ma_Caesar {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        CharacterService s = (CharacterService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMICharacterService");
        s.submitCharacter(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static String solve(String s) {
        StringBuilder r = new StringBuilder();
        int shift = s.length() % 7;
        for (char c : s.toCharArray()) {
            int base = c >= 'a' && c <= 'z' ? 'a' : c >= 'A' && c <= 'Z' ? 'A' : -1;
            r.append(base < 0 ? c : (char) (base + (c - base - shift + 26) % 26));
        }
        return r.toString();
    }
}
