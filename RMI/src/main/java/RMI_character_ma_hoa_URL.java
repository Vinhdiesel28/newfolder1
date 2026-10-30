import RMI.*;
import java.rmi.registry.*;

public class RMI_character_ma_hoa_URL {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        CharacterService s = (CharacterService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMICharacterService");
        s.submitCharacter(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static String solve(String s) throws Exception {
        return new java.net.URI(null, null, s, null).toASCIIString();
    }
}
