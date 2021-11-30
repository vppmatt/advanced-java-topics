package deadlock;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        StockLevel stockLevel = new StockLevel();

        Thread incrementer = new Incrementer(stockLevel);
        Thread decrementer =  new Decrementer(stockLevel);
        incrementer.start();
        decrementer.start();

        incrementer.join();
        decrementer.join();

        stockLevel.printTotal();

    }
}
