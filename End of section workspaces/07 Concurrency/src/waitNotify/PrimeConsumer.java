package waitNotify;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class PrimeConsumer implements Runnable {

    private final List<BigInteger> primes;
    private final int id;

    public PrimeConsumer(List<BigInteger> primes, int id) {
        this.primes = primes;
        this.id = id;
    }

    @Override
    public void run() {


        while (true) {
            //wait a random amount of time then request a prime number
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println("Consumer " + id + " is asking for a prime number");
            BigInteger prime = null;
            while (prime == null) {
                synchronized (primes) {
                    if (primes.size() == 0) {
                        System.out.println("Consumer " + id + "  is waiting...");
                        try {
                            primes.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (primes.size() > 0) {
                        prime = primes.remove(0);
                        System.out.println("Consumer " + id + "  got a prime number " + prime);
                        primes.notify();
                    }

                }
            }

        }
    }
}
