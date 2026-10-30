import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_object_sach_tao_ma_Book {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((Book) s.requestObject(msv, qCode)));
    }

    public static Book solve(Book b) {
        String[] author = b.getAuthor().trim().split("\\s+");
        String initials = ("" + author[0].charAt(0) + author[author.length - 1].charAt(0)).toUpperCase(Locale.ROOT);
        b.setCode(initials + String.format(Locale.US, "%02d", b.getYearPublished() % 100) + b.getTitle().length()
                + String.format(Locale.US, "%03d", b.getPageCount()));
        return b;
    }
}
