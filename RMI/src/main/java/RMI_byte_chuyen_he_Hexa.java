import RMI.*;
import java.rmi.registry.*;
import java.nio.charset.StandardCharsets;

public class RMI_byte_chuyen_he_Hexa {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ByteService s = (ByteService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIByteService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static byte[] solve(byte[] a) {
        StringBuilder r = new StringBuilder();
        for (byte x : a) r.append(String.format("%02x", x & 255));
        return r.toString().getBytes(StandardCharsets.US_ASCII);
    }
}
