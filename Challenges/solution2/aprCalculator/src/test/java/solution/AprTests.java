package solution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AprTests {

    @Mock
    DBUtils dbUtils;

    @Spy
    AprValidator aprValidator = new AprValidator();

    @InjectMocks
    PersistedAprValidator persistedAprValidator;

    @Test
    void testValues() throws ClassNotFoundException {
        LocalDate drawdownDate = LocalDate.of(2019,7,13);
        LocalDate firstRepaymentDate1 = LocalDate.of(2019,8,13);
        LocalDate firstRepaymentDate2 =  LocalDate.of(2019,8,26);
        Loan loan = new Loan(1, 10000, drawdownDate, firstRepaymentDate1, 60, 188.71);

        double expectedRate = 0.05107;

        doNothing().when(dbUtils).save(anyInt(), anyDouble());

        DecimalFormat df = new DecimalFormat("#.#####");
        double rate = persistedAprValidator.resolve(loan).get();
        assertEquals("0.05107", df.format(rate));
    }
}
