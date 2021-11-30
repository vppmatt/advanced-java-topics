package waitNotify;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class PrimeGenerator implements Runnable{

    private final List<BigInteger> primes;
    private final int id;

    public PrimeGenerator(List<BigInteger> primes, int id) {
        this.primes = primes;
        this.id = id;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("Generator " + id + " is calculating");
            BigInteger initialValue = new BigInteger(3000, new Random());
            BigInteger prime = initialValue.nextProbablePrime();
            boolean addedMyNumber = false;
            while (!addedMyNumber) {
                synchronized (primes) {
                    if (primes.size() >= 10) {
                        //System.out.println("Generator " + id + "  is waiting for space");
                        try {
                            primes.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (primes.size() < 10) {
                        primes.add(prime);
                        primes.notify();
                        //System.out.println("Generator  " + id + "  added a prime");
                        addedMyNumber = true;
                    }
                }
            }
        }
    }
}
