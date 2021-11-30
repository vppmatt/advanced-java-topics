package utilities;

        import exceptions.ConversionCalculationException;
        import exceptions.CurrencyNotFoundException;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.Optional;
        import java.util.logging.Level;
        import java.util.logging.Logger;

public class ExchangeRatesWithInaccessibleDependencies {

    private DatabaseUtils dbUtils = null;
    private final Logger logger = Logger.getLogger("ExchangeRates");

    private Double roundDouble(Double value) {
        return Math.floor(value * 100) / 100;
    }

    private Map<String,Double> getFees(String fromCurrency, String toCurrency, Double amount) throws CurrencyNotFoundException, ConversionCalculationException {
        Map<String,Double> fees = new HashMap<>();
        if (!fromCurrency.equals("USD") && !toCurrency.equals("USD")) {
            fees.put("non USD conversion supplement", simpleConvert("USD", toCurrency, 1.66));
        }
        if (simpleConvert(toCurrency, "USD", amount) < 100) {
            fees.put("small transaction charge", simpleConvert("USD", toCurrency, 3.49));
        }
        fees.put("conversion fee at 2.7%", roundDouble(amount * 0.027));
        return fees;
    }

    private Double simpleConvert(String fromCurrency, String toCurrency, Double amount) throws CurrencyNotFoundException {
        Double fromRate = dbUtils.getRate(fromCurrency);
        Double toRate = dbUtils.getRate(toCurrency);
        if (fromRate == null) throw new CurrencyNotFoundException(fromCurrency);
        if (toRate == null) throw new CurrencyNotFoundException(toCurrency);
        Double usdValue = amount * fromRate;
        Double targetValue= usdValue / toRate;
        return roundDouble(targetValue);
    }

    public Double convert(String fromCurrency, String toCurrency, Double amount) throws CurrencyNotFoundException, ConversionCalculationException {

        logger.log(Level.INFO, "Converting {0} {1} to {2}", new String[] {amount.toString(), fromCurrency, toCurrency});

        Double baseConversionValue = simpleConvert(fromCurrency, toCurrency, amount);
        logger.log(Level.INFO, "Equivalent in {0} is {1}", new String[] {toCurrency, baseConversionValue.toString()});

        Map<String, Double> feesMap = getFees(fromCurrency, toCurrency, baseConversionValue);
        Optional<Double> fees = feesMap.values().stream().reduce( (a, b) -> a + b);
        if (!fees.isPresent()) throw new ConversionCalculationException();

        Double totalFees = 0d;

        for( String feeName : feesMap.keySet()){
            Double fee = feesMap.get(feeName);
            logger.log(Level.INFO, feeName + " : " + fee +  " " + toCurrency);
            totalFees += fee;
        };

        logger.log(Level.INFO, "Total fees {0}", totalFees);

        Double baseAmountLessFees = baseConversionValue - totalFees;
        logger.log(Level.INFO, "Final conversion amount {0}", baseAmountLessFees);

        return roundDouble(baseAmountLessFees);
    }


}
