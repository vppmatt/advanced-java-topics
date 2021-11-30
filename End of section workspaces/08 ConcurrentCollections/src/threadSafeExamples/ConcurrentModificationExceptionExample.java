package threadSafeExamples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModificationExceptionExample {

    //final List<Integer> values = new ArrayList<>();
    //final List<Integer> values = Collections.synchronizedList(new ArrayList<>());
    final List<Integer> values = new CopyOnWriteArrayList<>();

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
