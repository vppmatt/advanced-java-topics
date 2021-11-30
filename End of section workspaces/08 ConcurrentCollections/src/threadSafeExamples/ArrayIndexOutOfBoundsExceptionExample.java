package threadSafeExamples;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;


public class ArrayIndexOutOfBoundsExceptionExample {

    //final List<Integer> values = new ArrayList<>();
    //final List<Integer> values = new CopyOnWriteArrayList<>();
    final Queue<Integer> values = new LinkedBlockingQueue<>();

    public void start() throws InterruptedException {

        for (int i = 1; i < 1000; i ++) {
            values.add(i);
            //note with a queue can use .offer(i) - will return a boolean which is false if the item
            //cannot be added because the queue is full
        }

        //remover thread 1
        Thread remover1 = new Thread( () -> {
            for (int i = 0; i < 500; i++) {
                System.out.println("removing " + i);
                    //int result = values.remove(values.size() - 1);
                    int result = values.poll();
                    System.out.println("got " + result);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //remover thread 2
        Thread remover2 = new Thread( () -> {
            for (int i = 0; i < 499; i++) {
                System.out.println("removing " + i);
                //int result = values.remove(values.size() - 1);
                int result = values.poll();
                System.out.println("got " + result);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        remover1.start();
        remover2.start();

        remover1.join();
        remover2.join();
    }
}
