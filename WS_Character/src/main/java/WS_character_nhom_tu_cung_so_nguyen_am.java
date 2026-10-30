import vn.medianews.*;
import java.util.*;
import java.net.URL;

public class WS_character_nhom_tu_cung_so_nguyen_am {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapCharacterService s = new CharacterService(new URL(args[2])).getSoapCharacterServicePort();
        s.submitCharacterStringArray(msv, qCode, solve(s.requestStringArray(msv, qCode)));
    }

    public static List<String> solve(List<String> a) {
        Map<Integer, List<String>> groups = new TreeMap<>();
        for (String word : a) groups.computeIfAbsent(vowels(word), k -> new ArrayList<>()).add(word);
        List<String> r = new ArrayList<>();
        for (List<String> group : groups.values()) { Collections.sort(group); r.add(String.join(", ", group)); }
        return r;
    }

    static int vowels(String s) {
        int count = 0;
        for (char c : s.toCharArray()) if ("aeiouAEIOU".indexOf(c) >= 0) count++;
        return count;
    }
}
