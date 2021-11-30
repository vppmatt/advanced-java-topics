package callableAndFutures;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class MainCompletableFutureVersion {

    public static BigInteger generatePrimeNumber() {
        BigInteger initialValue = new BigInteger(2000, new Random());
        BigInteger prime = initialValue.nextProbablePrime();
        return prime;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<BigInteger> primes = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++ ) {
            CompletableFuture<BigInteger> completableFuture =
                    CompletableFuture.supplyAsync( () -> generatePrimeNumber(), executorService);
            completableFuture.thenAccept(
                    value -> {
                        primes.add(value);
                    });
        }

        executorService.shutdown();
        while(!executorService.isTerminated()) {

        }
        System.out.println(primes);
    }
}
