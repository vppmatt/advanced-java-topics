package utilities;

import exceptions.ConversionCalculationException;
import exceptions.CurrencyNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeRates {

    private Map<String,Double> rates;
    private final Logger logger = Logger.getLogger("ExchangeRates");

    public ExchangeRates() {
        rates = new HashMap<>();
        rates.put("USD", 1.0000);  //US Dollar
        rates.put("GBP", 1.3440);  //UK Pound
        rates.put("EUR", 1.1276);  //Euro
        rates.put("JPY", 0.0087);  //Japanese Yen
        rates.put("CHF", 1.0821);  //Swiss Franc
        rates.put("CNY", 0.1614);  //Chinese Yuan
        rates.put("CAD", 0.7922);  //Canadian Dollar
        rates.put("MXN", 0.0481); //Mexican Peso
    }

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
        Double fromRate = rates.get(fromCurrency);
        Double toRate = rates.get(toCurrency);
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
