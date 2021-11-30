package threadBasics;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = new NumberCounter(1,1000);
        Runnable task2 = new NumberCounter(1001,2000);
        Runnable task3 = new Thread( () -> {
            for (int i = 2001; i < 3000; i++)
                System.out.println(Thread.currentThread().getName() +" " + i);
        });
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t2.setPriority(Thread.MAX_PRIORITY);
        Thread t3 = new Thread(task3, "Third thread");
        Thread t4 = new NumberCounterThread(3001,4000);

        Thread t5 = new Thread(new InterruptableTask());
        t5.start();

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.interrupt();

        System.out.println("Main method is complete");
    }
}
