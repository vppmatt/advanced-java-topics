package threadBasics;

public class NumberCounter implements Runnable {

    int min;
    int max;

    public NumberCounter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        int midpoint = min + (max - min) / 2;
        for (int i = min; i < max; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            //for debugging purposes
            if (i % midpoint == 0) {
                System.out.println(Thread.currentThread().getName() + " " + "half way");
            }
        }
    }
}
