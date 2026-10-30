import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_object_sinh_vien_chuan_hoa_ten_va_ma {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((Student) s.requestObject(msv, qCode)));
    }

    public static Student solve(Student s) {
        String[] words = s.getName().trim().toLowerCase(Locale.ROOT).split("\\s+");
        StringBuilder initials = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase(Locale.ROOT) + words[i].substring(1);
            if (i < words.length - 1) initials.append(words[i].charAt(0));
        }
        s.setCode("B" + String.format(Locale.US, "%02d", s.getEnrollmentYear() % 100)
                + words[words.length - 1].toUpperCase(Locale.ROOT) + "_" + initials);
        s.setName(String.join(" ", words));
        return s;
    }
}
