import RMI.*;
import java.rmi.registry.*;

public class RMI_object_phan_tang_xu_ly_SLA_ticket {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((TicketSla) s.requestObject(msv, qCode)));
    }

    public static TicketSla solve(TicketSla t) {
        int limit;
        switch (t.getPriority()) {
            case "CRITICAL": limit = 2; break;
            case "HIGH": limit = 8; break;
            case "MEDIUM": limit = 24; break;
            case "LOW": limit = 72; break;
            default: throw new IllegalArgumentException(t.getPriority());
        }
        boolean breached = t.getOpenedHoursAgo() > limit;
        t.setBreached(breached);
        t.setAction(!breached ? "MONITOR" :
                ("CRITICAL".equals(t.getPriority()) || t.getOpenedHoursAgo() > 96) ? "ESCALATE_L2" : "ESCALATE_L1");
        return t;
    }
}
