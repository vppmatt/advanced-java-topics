package callableAndFutures;

import throughputEvaluation.GeneratePrimeNumberTask;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainFutureVersion {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Future<BigInteger>> futures = new ArrayList<>();
        List<BigInteger> primes = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++ ) {
           Future<BigInteger> future =  executorService.submit(new GeneratePrimeNumber());
           futures.add(future);
        }

        for (Future<BigInteger> future : futures) {
            primes.add(future.get());
        }
        System.out.println(primes);
    }
}
