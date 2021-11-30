package sharePriceManager;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharePricesContainer container = new SharePricesContainer();
        PriceUpdateManager manager = new PriceUpdateManager(container);
        manager.start();

        while(true) {
            Thread.sleep(5000);
            System.out.println("requesting prices...");
            container.getLock().lock();
            System.out.println(container.getAll());
            container.getLock().unlock();
        }
    }
}
