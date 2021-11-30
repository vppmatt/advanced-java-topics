import exceptions.ConversionCalculationException;
import exceptions.CurrencyNotFoundException;
import utilities.DatabaseUtils;
import utilities.ExchangeRates;
import utilities.ExchangeRatesWithDependencyInjection;

public class Main {

    public static void main(String[] args) throws ConversionCalculationException, CurrencyNotFoundException {

        DatabaseUtils dbUtils = new DatabaseUtils();
        dbUtils.initialiseDatabase();

        ExchangeRates exchangeRates = new ExchangeRates();
        Double firstExample = exchangeRates.convert("GBP","EUR",320d);
        Double secondExample = exchangeRates.convert("GBP","USD",60d);
        }
}
