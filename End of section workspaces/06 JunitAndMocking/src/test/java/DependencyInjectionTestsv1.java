import exceptions.ConversionCalculationException;
import exceptions.CurrencyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.DatabaseUtils;
import utilities.ExchangeRatesWithDependencyInjection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DependencyInjectionTestsv1 {

    DatabaseUtils dbUtils = mock(DatabaseUtils.class);
    ExchangeRatesWithDependencyInjection exchangeRates = new ExchangeRatesWithDependencyInjection(dbUtils);

    @BeforeEach
    public void beforeEach() throws CurrencyNotFoundException {
        when(dbUtils.getRate( anyString())).thenThrow(new CurrencyNotFoundException(""));

        //use this format to override anyString...
        doReturn(1.4).when(dbUtils).getRate("GBP");
        doReturn(1.5).when(dbUtils).getRate("EUR");
        doReturn(1.0).when(dbUtils).getRate("USD");

    }

    @Test
    public void testEURtoGBP() throws ConversionCalculationException, CurrencyNotFoundException {
        String fromCurrency = "EUR";
        String toCurrency = "GBP";
        Double startAmount = 100d;
        Double expectedTotal = 103.07;
        Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void testThatSimpleConvertIsCalledAtLeastTwice() throws CurrencyNotFoundException, ConversionCalculationException {
        String fromCurrency = "GBP";
        String toCurrency = "EUR";
        Double startAmount = 150d;
        Double expectedTotal = 196.16;
        Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        verify(dbUtils, atLeast(3)).getRate(anyString());
    }

    @Test
    public void testUnknownCurrency() {
        assertThrows(CurrencyNotFoundException.class, () ->{
            String fromCurrency = "XXX";
            String toCurrency = "GBP";
            Double startAmount = 19.61;
            Double expectedTotal = 14.20;
            Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        });
    }

}
