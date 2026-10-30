import vn.medianews.*;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class WS_character_che_va_sap_xep_log {
    private static final Pattern LEVEL = Pattern.compile("(?i)\\b(ERROR|WARN|INFO)\\b");

    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        SoapCharacterService s = new CharacterService(new URL(args[2])).getSoapCharacterServicePort();
        s.submitStringArray(msv, qCode, solve(s.requestStringArray(msv, qCode)));
    }

    public static List<String> solve(List<String> a) {
        List<String> r = new ArrayList<>();
        for (String log : a) {
            log = log.replaceAll("(?i)(\\bemail\\s*=\\s*)\\S+", "$1[EMAIL]");
            log = log.replaceAll("(?i)(\\bphone\\s*=\\s*)\\S+", "$1[PHONE]");
            log = log.replaceAll("(?i)(\\btoken\\s*=\\s*)\\S+", "$1[TOKEN]");
            r.add(log);
        }
        r.sort(Comparator.comparingInt(WS_character_che_va_sap_xep_log::level));
        return r;
    }

    private static int level(String s) {
        Matcher m = LEVEL.matcher(s);
        if (!m.find()) return 3;
        return switch (m.group(1).toUpperCase(Locale.ROOT)) {
            case "ERROR" -> 0;
            case "WARN" -> 1;
            default -> 2;
        };
    }
}
