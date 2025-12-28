import org.junit.jupiter.api.Test;
import com.clinic.services.ClinicService;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    @Test
    void testBooking(){
        ClinicService service = new ClinicService();
        LocalDate date = LocalDate.parse("2025-12-28");
        LocalTime slot = LocalTime.parse("09:00");
        service.book("john","Dr Smith", date, slot);

        assertTrue(service.isSlotTaken("Dr Smith", date, slot));
    }
}

