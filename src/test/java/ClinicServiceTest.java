import org.junit.jupiter.api.Test;
import com.clinic.services.ClinicService;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicServiceTest {

    @Test
    void testPatientRegistration(){
        ClinicService auth = new ClinicService();
        assertTrue(auth.registerPatient("john","1234"));
    }
}
