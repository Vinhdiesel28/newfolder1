import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_character_tach_JSON_vi_tri_chan_le {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        CharacterService s = (CharacterService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMICharacterService");
        s.submitCharacter(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static String solve(String s) {
        List<String> fields = JsonChuoi.fields(s);
        StringJoiner even = new StringJoiner(", "), odd = new StringJoiner(", ");
        for (int i = 0; i < fields.size(); i++) (i % 2 == 0 ? even : odd).add(fields.get(i));
        return even + "; " + odd;
    }
}
