import com.clinic.models.Patient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Test
    void patientInheritanceTest(){
        Patient p = new Patient("john","1234");
        assertEquals("john", p.getUsername());
    }
}

