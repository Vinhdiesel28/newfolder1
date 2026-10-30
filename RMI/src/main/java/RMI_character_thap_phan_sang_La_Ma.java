import RMI.*;
import java.rmi.registry.*;

public class RMI_character_thap_phan_sang_La_Ma {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        CharacterService s = (CharacterService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMICharacterService");
        s.submitCharacter(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static String solve(String s) {
        int n = Integer.parseInt(s.trim());
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < values.length; i++) while (n >= values[i]) { r.append(symbols[i]); n -= values[i]; }
        return r.toString();
    }
}
