import RMI.*;
import java.rmi.registry.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class RMI_object_ve_su_kien_tao_ma {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((Ticket) s.requestObject(msv, qCode)));
    }

    public static Ticket solve(Ticket t) {
        String name = t.getEventName().trim(), date = t.getSaleDate();
        int low = 0, high = 9;
        while (date.indexOf((char) ('0' + low)) >= 0) low++;
        while (date.indexOf((char) ('0' + high)) >= 0) high--;
        String initials = ("" + name.charAt(0) + name.charAt(name.length() - 1)).toUpperCase(Locale.ROOT);
        String monthDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("MMdd"));
        t.setTicketCode(initials + monthDay + high + low);
        return t;
    }
}
