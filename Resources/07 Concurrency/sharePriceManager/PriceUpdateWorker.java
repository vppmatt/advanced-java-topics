package sharePriceManager;

import java.util.Random;

public class PriceUpdateWorker extends Thread{

    private final String company;
    private final SharePricesContainer container;
    private final Random random = new Random();


    public PriceUpdateWorker(String company, SharePricesContainer container) {
        super();
        this.company = company;
        this.container = container;
    }

    @Override
    public void run() {
            //simulate getting the share price time delay
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                System.out.println("Price update worker for " + company + " got interrupted.");
                e.printStackTrace();
            }
            container.setPrice(company, random.nextDouble() * 1000);
    }
}
