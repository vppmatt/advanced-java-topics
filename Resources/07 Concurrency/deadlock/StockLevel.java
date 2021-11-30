package deadlock;

public class StockLevel {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    int i = 0;
    int hits = 0;

    public void addOne() {
        synchronized(lock1) {
            System.out.println("add - Got lock 1");
            hits++;
            synchronized (lock2) {
                System.out.println("add - Got lock 2");
                i++;
            }

        }
    }

    public void subtractOne() {
        synchronized(lock2) {
            System.out.println("subtract - Got lock 2");
            i--;
            synchronized (lock1) {
                System.out.println("subtract - Got lock 1");
                hits++;
            }

        }

    }

    //this is atomic
    public void printTotal() {
        System.out.println("total is " + i + " after " + hits + " hits");
    }
}
