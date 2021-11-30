import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class countdownLatch {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(10);

        Thread primeGenerator = new Thread(() -> {
            while(latch.getCount() > 0) {
                BigInteger initialValue = new BigInteger(2000, new Random());
                System.out.println( initialValue.nextProbablePrime());
                latch.countDown();
            }
        });

        primeGenerator.start();
        System.out.println("Waiting for 10 primes");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("10 primes were found");
    }
}
