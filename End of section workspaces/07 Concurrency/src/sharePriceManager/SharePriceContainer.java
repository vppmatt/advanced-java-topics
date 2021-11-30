package sharePriceManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class SharePriceContainer {

    private Map<String, Double> currentSharePrices = new HashMap<>();
    private Map<String, Double> nextSharePrices = new HashMap<>();

    private final ReentrantLock lock = new ReentrantLock();

    public void setPrice(String company, double price) {
        if (price < 0) {
            System.out.println("initialising " + company);
        } else {
            System.out.println("got share price for " + company);
        }
        lock.lock();
        nextSharePrices.put(company, price);
        lock.unlock();

    }

    public void updateCurrentPrices() {
        //note this is atomic as it's just a reference
        System.out.println("about to update");
        currentSharePrices = nextSharePrices;
        lock.lock();
        nextSharePrices = new HashMap<>();
        lock.unlock();
        System.out.println("Share prices updated");
    }

    public void getAll() {
        lock.lock();
        System.out.println(currentSharePrices);
        lock.unlock();
    }

}
