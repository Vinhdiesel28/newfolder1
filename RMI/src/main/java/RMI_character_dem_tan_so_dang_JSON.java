import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_character_dem_tan_so_dang_JSON {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        CharacterService s = (CharacterService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMICharacterService");
        s.submitCharacter(msv, qCode, solve(s.requestCharacter(msv, qCode)));
    }

    public static String solve(String s) {
        Map<String, Integer> f = new LinkedHashMap<>();
        s.codePoints().forEach(c -> f.merge(new String(Character.toChars(c)), 1, Integer::sum));
        StringJoiner r = new StringJoiner(", ", "{", "}");
        f.forEach((c, n) -> r.add(JsonChuoi.quote(c) + ": " + n));
        return r.toString();
    }
}
