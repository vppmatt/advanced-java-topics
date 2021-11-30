package raceConditions;

public class Incrementer extends Thread {

    StockLevel stockLevel;

    public Incrementer(StockLevel stockLevel) {
        this.stockLevel = stockLevel;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            stockLevel.addOne();
        }
    }
}

//share ref: 21g271qa
