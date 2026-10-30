import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_byte_phan_tu_lon_thu_K {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ByteService s = (ByteService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIByteService");
        byte[] a = s.requestData(msv, qCode), r = solve(a);
        if (args.length > 4 && args[4].equals("giao_trinh")) r[1] = a[a.length - 1];
        s.submitData(msv, qCode, r);
    }

    public static byte[] solve(byte[] a) {
        int k = a[a.length - 1] & 255;
        byte[] b = Arrays.copyOf(a, a.length - 1);
        Arrays.sort(b);
        byte value = b[b.length - k];
        int position = 0;
        while (a[position] != value) position++;
        return new byte[]{value, (byte) (position + 1)};
    }
}
