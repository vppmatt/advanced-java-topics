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

    @Test
    public void testGeneralToUSD() throws ConversionCalculationException, CurrencyNotFoundException {
        ExchangeRates exchangeRates = new ExchangeRates();
        String fromCurrency = "GBP";
        String toCurrency = "USD";
        Double startAmount = 150d;
        Double expectedTotal = 196.16;
        Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void testGeneralfromUSD() throws ConversionCalculationException, CurrencyNotFoundException {
        ExchangeRates exchangeRates = new ExchangeRates();
        String fromCurrency = "USD";
        String toCurrency = "GBP";
        Double startAmount = 196.16;
        Double expectedTotal = 142.01;
        Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void testNonUSD() throws ConversionCalculationException, CurrencyNotFoundException {
        ExchangeRates exchangeRates = new ExchangeRates();
        String fromCurrency = "CAD";
        String toCurrency = "MXN";
        Double startAmount = 600d;
        Double expectedTotal = 9580.59;
        Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void testSmallTransactionCharge() throws ConversionCalculationException, CurrencyNotFoundException {
        ExchangeRates exchangeRates = new ExchangeRates();
        String fromCurrency = "USD";
        String toCurrency = "GBP";
        Double startAmount = 19.61;
        Double expectedTotal = 11.61;
        Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    public void testUnknownCurrency() {
        ExchangeRates exchangeRates = new ExchangeRates();

        assertThrows(CurrencyNotFoundException.class, () ->{
            String fromCurrency = "XXX";
            String toCurrency = "GBP";
            Double startAmount = 19.61;
            Double expectedTotal = 14.20;
            Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        });
    }
}
