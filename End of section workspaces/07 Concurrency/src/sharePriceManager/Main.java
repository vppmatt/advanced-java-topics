package sharePriceManager;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        SharePriceContainer container = new SharePriceContainer();
        PriceUpdateManager manager = new PriceUpdateManager(container);
        manager.start();

        while(true) {
            Thread.sleep(5000);
            System.out.println("requesting prices...");
            container.getAll();
        }
    }
}
