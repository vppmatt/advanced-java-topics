package nonThreadSafeExample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentModificationExceptionExample {

    //This example creates a list of 1000 integers.
    //It has one thread which removes an entry from the list every 500 ms.
    //And another which calculates and prints out the total acheived by adding all the entries

    final List<Integer> values = new ArrayList<>();

    public void start() throws InterruptedException {

        for (int i = 1; i < 1000; i ++) {
            values.add(1);
        }

        //remover thread
        Thread remover = new Thread( () -> {
            for (int i = 0; i < 1000; i++) {
                values.remove(values.size() -1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //iterator thread
        Thread iterator = new Thread(() -> {
            while(true) {
               int total = values.stream().reduce( (a, b) -> a + b).get();
                System.out.println(total);
            }
        });

        iterator.start();
        remover.start();

        iterator.join();
        remover.join();
    }

}
