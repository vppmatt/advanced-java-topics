import exceptions.ConversionCalculationException;
import exceptions.CurrencyNotFoundException;
import org.junit.jupiter.api.Test;
import utilities.ExchangeRates;

import static org.junit.jupiter.api.Assertions.*;

public class BasicTests {

    /*
    Test 1 - general conversion to USD:    150.00 GBP = 196.16 USD
    Test 2 - general conversion from USD:  196.16 USD = 142.01 GBP
    Test 3 - general conversion non USD:   600.00 CAD = 9580.59 MXN
    Test 4 - small value conversion:       19.61 USD = 11.61 GBP
    Test 5 - Exception testing - currency XXX
     */

}
