package sharePriceManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharePricesContainer {


    private final Map<String, Double> sharePrices = new HashMap<>();

    private final ReentrantLock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public void setPrice(String company, double price) {

        if (price < 0) {
            System.out.println("initialising " + company);
        } else {
            System.out.println("got share price for " + company);
        }
        sharePrices.put(company, price);
    }

    public Map<String, Double> getAll() {
            return sharePrices;
    }


}
