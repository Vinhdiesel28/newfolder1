import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_character_tu_dai_va_ngan_nhat_trong_chuoi {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapCharacterService s = new CharacterService(new URL(args[2])).getSoapCharacterServicePort();
        s.submitCharacterString(msv, qCode, solve(s.requestString(msv, qCode)));
    }

    public static String solve(String s) {
        List<String> a = Arrays.asList(s.trim().split("\\s+"));
        String longest = a.get(0), shortest = a.get(0);
        for (String word : a) {
            if (word.length() > longest.length()) longest = word;
            if (word.length() < shortest.length()) shortest = word;
        }
        return longest + "; " + shortest;
    }
}
