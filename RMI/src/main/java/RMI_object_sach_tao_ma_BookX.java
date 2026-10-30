import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_object_sach_tao_ma_BookX {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((BookX) s.requestObject(msv, qCode)));
    }

    public static BookX solve(BookX b) {
        String author = b.getAuthor().trim();
        b.setCode("" + author.charAt(0) + author.charAt(author.length() - 1)
                + String.format(Locale.US, "%02d", b.getYearPublished() % 100) + b.getGenre().length() + b.getTitle().length() % 10);
        return b;
    }
}
