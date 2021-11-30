package raceConditions;

public class StockLevel {

    int i = 0;

    public void addOne() {
        i++;
    }

    public void subtractOne() {
        i--;
    }

    public void printTotal() {
        System.out.println("total is " + i);
    }
}
