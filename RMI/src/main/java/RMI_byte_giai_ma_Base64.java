import RMI.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_byte_giai_ma_Base64 {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ByteService s = (ByteService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIByteService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static byte[] solve(byte[] a) {
        return Base64.getDecoder().decode(a);
    }
}
