import java.time.*;
import java.time.format.*;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

public class WSNgay {
    public static LocalDate ngay(Object value) {
        if (value instanceof XMLGregorianCalendar x) return LocalDate.of(x.getYear(), x.getMonth(), x.getDay());
        if (value instanceof Date d) return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String s = value.toString().trim();
        for (String format : new String[]{"yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy"}) {
            try { return LocalDate.parse(s.substring(0, 10), DateTimeFormatter.ofPattern(format)); }
            catch (RuntimeException ignored) {}
        }
        throw new IllegalArgumentException(s);
    }
}
