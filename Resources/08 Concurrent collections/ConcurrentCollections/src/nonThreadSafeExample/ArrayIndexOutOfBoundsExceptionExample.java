package nonThreadSafeExample;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;


public class ArrayIndexOutOfBoundsExceptionExample {

    //This example creates a list of 1000 integers.
    //It has two threads which each remove *the last* entry from the list every 500 ms.

    final List<Integer> values = new ArrayList<>();

    public void start() throws InterruptedException {

        for (int i = 1; i < 1000; i ++) {
            values.add(i);
        }

        //remover thread 1
        Thread remover1 = new Thread( () -> {
            for (int i = 0; i < 500; i++) {
                System.out.println("removing " + i);
                int result = values.remove(values.size() -1);
                //int result = values.remove();
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
                int result = values.remove(values.size() -1);
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
