import RMI.*;
import java.rmi.registry.*;
import java.nio.charset.StandardCharsets;

public class RMI_byte_ma_hoa_XOR_PTIT {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ByteService s = (ByteService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIByteService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static byte[] solve(byte[] a) {
        byte[] r = a.clone(), key = "PTIT".getBytes(StandardCharsets.US_ASCII);
        for (int i = 0; i < r.length; i++) r[i] ^= key[i % key.length];
        return r;
    }
}
