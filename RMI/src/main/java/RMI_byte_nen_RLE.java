import RMI.*;
import java.rmi.registry.*;

public class RMI_byte_nen_RLE {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ByteService s = (ByteService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIByteService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static byte[] solve(byte[] a) {
        java.io.ByteArrayOutputStream r = new java.io.ByteArrayOutputStream();
        for (int i = 0, j; i < a.length; i = j) {
            for (j = i + 1; j < a.length && a[j] == a[i]; j++);
            r.write(a[i]); r.write(j - i);
        }
        return r.toByteArray();
    }
}
