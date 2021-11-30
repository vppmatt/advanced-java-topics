package threadBasics;

public class NumberCounterThread extends Thread {
    int min;
    int max;

    public NumberCounterThread(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        int midpoint = min + (max - min) / 2;
        for (int i = min; i < max; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }
}
