package synchronization;

public class StockLevel {

    int i = 0;

    public synchronized void  addOne() {
        i++;
    }

    public void subtractOne() {
        synchronized(this) {
            i--;
        }

    }

    //this is atomic
    public void printTotal() {
        System.out.println("total is " + i);
    }
}
