import RMI.*;
import java.rmi.registry.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class RMI_object_su_kien_tao_ma {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((Event) s.requestObject(msv, qCode)));
    }

    public static Event solve(Event e) {
        String name = e.getEventName().trim();
        String size = e.getExpectedAttendance() >= 1000 ? "L" : e.getExpectedAttendance() >= 500 ? "M" : "S";
        String initials = ("" + name.charAt(0) + name.charAt(name.length() - 1)).toUpperCase(Locale.ROOT);
        String date = LocalDate.parse(e.getEventDate()).format(DateTimeFormatter.ofPattern("ddMM"));
        e.setEventCode(size + initials + date);
        return e;
    }
}
