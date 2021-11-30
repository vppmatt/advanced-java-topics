package sharePriceManager;

import java.util.ArrayList;
import java.util.List;

public class PriceUpdateManager extends Thread {

    private final SharePricesContainer container;

    public PriceUpdateManager(SharePricesContainer container) {
        super();
        this.container = container;
    }

    @Override
    public void run() {

        String[] companies = new String[] {"apple", "microsoft", "facebook", "google", "samsung"};

        List<Thread> threads = new ArrayList<>();

        for (String company : companies) {
            container.setPrice(company, -1);
            threads.add(new PriceUpdateWorker(company, container));
        }

        while(true) {
            container.getLock().lock();
            for (Thread t : threads) {
                t.run();
            }
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            container.getLock().unlock();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
