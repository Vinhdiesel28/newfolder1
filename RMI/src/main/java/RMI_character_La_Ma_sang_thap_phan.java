import RMI.*;
import java.rmi.registry.*;

public class RMI_character_La_Ma_sang_thap_phan {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        CharacterService s = (CharacterService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMICharacterService");
        s.submitCharacter(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static String solve(String s) {
        int result = 0, previous = 0;
        String symbols = "IVXLCDM";
        int[] values = {1, 5, 10, 50, 100, 500, 1000};
        for (int i = s.length() - 1; i >= 0; i--) {
            int value = values[symbols.indexOf(s.charAt(i))];
            result += value < previous ? -value : value;
            previous = value;
        }
        return Integer.toString(result);
    }
}
