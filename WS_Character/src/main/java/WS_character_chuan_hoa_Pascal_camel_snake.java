import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_character_chuan_hoa_Pascal_camel_snake {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapCharacterService s = new CharacterService(new URL(args[2])).getSoapCharacterServicePort();
        s.submitCharacterStringArray(msv, qCode, solve(s.requestString(msv, qCode)));
    }

    public static List<String> solve(String s) {
        String[] words = s.trim().toLowerCase(Locale.ROOT).split("[\\s_]+");
        StringBuilder pascal = new StringBuilder(), camel = new StringBuilder();
        for (int i = 0; i < words.length; i++) if (!words[i].isEmpty()) {
            String title = words[i].substring(0, 1).toUpperCase(Locale.ROOT) + words[i].substring(1);
            pascal.append(title); camel.append(i == 0 ? words[i] : title);
        }
        return Arrays.asList(pascal.toString(), camel.toString(), String.join("_", words));
    }
}
