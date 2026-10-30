import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_byte_xuat_hien_it_nhat {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ByteService s = (ByteService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIByteService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static byte[] solve(byte[] a) {
        Map<Byte, Integer> f = new LinkedHashMap<>();
        for (byte x : a) f.merge(x, 1, Integer::sum);
        byte value = a[0];
        for (byte x : f.keySet()) if (f.get(x) < f.get(value)) value = x;
        return new byte[]{value, f.get(value).byteValue()};
    }
}
