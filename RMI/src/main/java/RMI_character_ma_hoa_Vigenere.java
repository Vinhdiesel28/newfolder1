import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_character_ma_hoa_Vigenere {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        CharacterService s = (CharacterService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMICharacterService");
        s.submitCharacter(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static String solve(String s) {
        String[] p = s.split(";", 2);
        String key = p[0].toUpperCase(Locale.ROOT), text = p[1];
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int base = c >= 'a' && c <= 'z' ? 'a' : c >= 'A' && c <= 'Z' ? 'A' : -1;
            r.append(base < 0 ? c : (char) (base + (c - base + key.charAt(i % key.length()) - 'A') % 26));
        }
        return r.toString();
    }
}
