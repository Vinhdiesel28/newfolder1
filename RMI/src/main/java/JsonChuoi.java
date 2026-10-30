import java.util.*;

public class JsonChuoi {
    public static String quote(String s) {
        StringBuilder r = new StringBuilder("\"");
        for (char c : s.toCharArray()) {
            switch (c) {
                case '"' -> r.append("\\\"");
                case '\\' -> r.append("\\\\");
                case '\n' -> r.append("\\n");
                case '\r' -> r.append("\\r");
                case '\t' -> r.append("\\t");
                default -> {
                    if (c < 32) r.append(String.format("\\u%04x", (int) c));
                    else r.append(c);
                }
            }
        }
        return r.append('"').toString();
    }

    static String unquote(String s) {
        s = s.trim();
        if (!s.startsWith("\"")) return s;
        StringBuilder r = new StringBuilder();
        for (int i = 1; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (c == '\\') {
                c = s.charAt(++i);
                switch (c) {
                    case 'n' -> c = '\n';
                    case 'r' -> c = '\r';
                    case 't' -> c = '\t';
                    case 'b' -> c = '\b';
                    case 'f' -> c = '\f';
                    case 'u' -> { c = (char) Integer.parseInt(s.substring(i + 1, i + 5), 16); i += 4; }
                }
            }
            r.append(c);
        }
        return r.toString();
    }

    static int separator(String s, char target, int start) {
        boolean quoted = false, escaped = false;
        int depth = 0;
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (quoted) {
                if (escaped) escaped = false;
                else if (c == '\\') escaped = true;
                else if (c == '"') quoted = false;
            } else if (c == '"') quoted = true;
            else if (c == '[' || c == '{') depth++;
            else if (c == ']' || c == '}') depth--;
            else if (c == target && depth == 0) return i;
        }
        return s.length();
    }

    public static List<String> fields(String s) {
        s = s.trim();
        s = s.substring(1, s.length() - 1);
        List<String> r = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            int end = separator(s, ',', i);
            String pair = s.substring(i, end).trim();
            int colon = separator(pair, ':', 0);
            r.add(unquote(pair.substring(0, colon)) + ": " + unquote(pair.substring(colon + 1)));
            i = end + 1;
        }
        return r;
    }
}
