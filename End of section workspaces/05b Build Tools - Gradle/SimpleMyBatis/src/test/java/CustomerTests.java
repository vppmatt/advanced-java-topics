import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTests {

    @Test
    public void testCustomerAge() {
        Customer c1 = new Customer(1,"Someone",39);
        assertEquals(39, c1.getAge());
//        fail();
    }
}
