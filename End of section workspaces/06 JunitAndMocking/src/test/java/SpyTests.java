import exceptions.ConversionCalculationException;
import exceptions.CurrencyNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import utilities.DatabaseUtils;
import utilities.ExchangeRatesWithDependencyInjection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class SpyTests {

    @Spy
    DatabaseUtils dbUtils;

    @InjectMocks
    ExchangeRatesWithDependencyInjection exchangeRates;

    @Test
    public void spyExample() throws CurrencyNotFoundException, ConversionCalculationException {

        doReturn(1.0).when(dbUtils).getRate(anyString());
        String fromCurrency = "GBP";
        String toCurrency = "EUR";
        Double startAmount = 150d;
        Double expectedTotal = 144.29;
        Double actualTotal = exchangeRates.convert(fromCurrency, toCurrency, startAmount);
        assertEquals(expectedTotal, actualTotal);

        assertEquals("3.2.7", dbUtils.version());
    }
}
