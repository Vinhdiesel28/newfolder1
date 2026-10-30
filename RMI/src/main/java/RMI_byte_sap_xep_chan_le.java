import RMI.*;
import java.rmi.registry.*;

public class RMI_byte_sap_xep_chan_le {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ByteService s = (ByteService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIByteService");
        s.submitData(msv, qCode, solve(s.requestData(msv, qCode)));
    }

    public static byte[] solve(byte[] a) {
        byte[] r = new byte[a.length];
        int i = 0;
        for (byte x : a) if (x % 2 == 0) r[i++] = x;
        for (byte x : a) if (x % 2 != 0) r[i++] = x;
        return r;
    }
}
