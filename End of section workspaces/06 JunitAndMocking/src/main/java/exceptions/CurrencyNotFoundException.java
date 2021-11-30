package exceptions;

public class CurrencyNotFoundException extends Exception {

    public CurrencyNotFoundException(String currency) {
        super(currency);
    }

    public CurrencyNotFoundException() {
        super();
    }
}
