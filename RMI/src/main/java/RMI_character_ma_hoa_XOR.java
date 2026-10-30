import RMI.*;
import java.rmi.registry.*;

public class RMI_character_ma_hoa_XOR {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        CharacterService s = (CharacterService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMICharacterService");
        s.submitCharacter(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static String solve(String s) {
        String[] p = s.split(";", 2);
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < p[1].length(); i++) r.append((char) (p[1].charAt(i) ^ p[0].charAt(i % p[0].length())));
        return r.toString();
    }
}
