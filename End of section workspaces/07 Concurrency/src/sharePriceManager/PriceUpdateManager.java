package sharePriceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class PriceUpdateManager extends Thread {


    private final SharePriceContainer container;

    public PriceUpdateManager(SharePriceContainer container) {
        super();
        this.container = container;
    }

    @Override
    public void run() {

        String[] companies = new String[] {"apple", "microsoft", "facebook", "google", "samsung"};

        List<PriceUpdateWorker> tasks = new ArrayList<>();

        for (String company : companies) {
            container.setPrice(company, -1);
            tasks.add(new PriceUpdateWorker(company));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        while(true) {
            Map<String, CompletableFuture<Double>> results = new HashMap<>();
            for (PriceUpdateWorker task : tasks) {
               CompletableFuture<Double> result = CompletableFuture
                       .supplyAsync(task::call, executorService);
               results.put(task.getCompany(), result);
            }

            //block until all complete or 5 seconds
            boolean ready = false;
            boolean timeout = false;
            long startTime = System.currentTimeMillis();
            while (! ready) {
                if (results.values().stream().filter( future -> !future.isDone()).count() == 0) {
                    ready = true;
                }
                if(System.currentTimeMillis() -5000 > startTime) {
                    ready = true;
                    timeout = true;
                }
            }

            if (timeout)  {
                System.out.println("finished executing but 1 or more failed so ignoring this result set");
            }
            else {
//                results.forEach( (company, price) -> {
//                    try {
//                        container.setPrice(company, price.get());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                });
                results.keySet().parallelStream().forEach( company -> {
                    try {
                        container.setPrice(company, results.get(company).get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
                container.updateCurrentPrices();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
