import RMI.*;
import java.rmi.registry.*;

public class RMI_object_nhan_vien_tinh_luong {
    public static void main(String[] args) throws Exception {
        String msv = args[0], qCode = args[1];
        ObjectService s = (ObjectService) LocateRegistry.getRegistry(args[2], Integer.parseInt(args[3])).lookup("RMIObjectService");
        s.submitObject(msv, qCode, solve((Employee) s.requestObject(msv, qCode)));
    }

    public static Employee solve(Employee e) {
        int years = e.getExperienceYears(), sum = 0, divisors = 0;
        for (int n = years; n > 0; n /= 10) sum += n % 10;
        for (int i = 1; i <= years / i; i++) if (years % i == 0) divisors += i == years / i ? 1 : 2;
        e.setFinalSalary(e.getBaseSalary() * (1 + (years + sum + divisors) / 100.0));
        return e;
    }
}
