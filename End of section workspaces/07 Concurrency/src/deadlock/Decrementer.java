package deadlock;

public class Decrementer extends Thread {

    StockLevel stockLevel;

    public Decrementer(StockLevel stockLevel) {
        this.stockLevel = stockLevel;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            stockLevel.subtractOne();
        }
    }
}

//share ref: 21g271qa
