package throughputEvaluation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 10; i++)
        {
            executorService.execute(new GeneratePrimeNumberTask());
        }
        executorService.shutdown();
        while(!executorService.isTerminated()) {

        }
        Long end = System.currentTimeMillis();
        System.out.println("completed in " + (end - start) + " ms.");
    }


}
