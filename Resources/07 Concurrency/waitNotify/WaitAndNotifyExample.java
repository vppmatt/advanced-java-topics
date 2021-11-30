package waitNotify;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitAndNotifyExample {

     final List<BigInteger> primes = new LinkedList<>();

     final int NUMBER_OF_GENERATORS = 3;
     final int NUMBER_OF_USERS = 2;

    public void start() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_GENERATORS + NUMBER_OF_USERS + 1);

        //monitor thread
        executorService.execute( () -> {
            while(true) {
                synchronized (primes) {
                    System.out.println("There are " + primes.size() + " primes available right now");
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //consumers
        for(int i = 0; i < NUMBER_OF_USERS; i++) {
            executorService.execute(new PrimeConsumer(primes, i));
        }
            //producers
            for (int i = 0; i< NUMBER_OF_GENERATORS; i++) {
                executorService.execute(new PrimeGenerator(primes, i));
        }

        executorService.shutdown();
        while(!executorService.isTerminated()) {

        }

    }

}
