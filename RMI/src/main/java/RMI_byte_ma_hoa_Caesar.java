import RMI.*;
import java.rmi.registry.*;

public class RMI_byte_ma_hoa_Caesar {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ByteService s = (ByteService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIByteService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static byte[] solve(byte[] a) {
        byte[] r = a.clone();
        for (int i = 0; i < r.length; i++) r[i] = (byte) (r[i] + r.length);
        return r;
    }
}
