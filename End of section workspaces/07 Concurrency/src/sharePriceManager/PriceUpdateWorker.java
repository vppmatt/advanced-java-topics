package sharePriceManager;

import java.util.Random;
import java.util.concurrent.Callable;

public class PriceUpdateWorker implements Callable<Double> {

    private final String company;
    private final Random random = new Random();

    public PriceUpdateWorker(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public Double call()  {
        //simulate getting the share price time delay
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            System.out.println("Price update worker for " + company + " got interrupted.");
            e.printStackTrace();
            return null;
        }

        //simulate no response
        int r = random.nextInt(20);
        if (r == 5) {
            System.out.println("Oh no - getting the price for " + company + " has crashed");
            while(true){}
        }

        return random.nextDouble() * 1000;

    }
}
