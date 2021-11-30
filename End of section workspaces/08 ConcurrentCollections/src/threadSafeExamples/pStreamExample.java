package threadSafeExamples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class pStreamExample {
    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            integers.add(i);
        }

        //how many threads are being used?
        integers.parallelStream().forEach( value -> {
            System.out.println(Thread.currentThread().getName() + " " + value);
        });

        //good operation - order doesn't matter
        int total1 = integers.stream().reduce(0, (a,b) -> a+b);
        int total2 = integers.parallelStream().reduce(0, (a,b) -> a+b);
        System.out.println(total1);
        System.out.println(total2);

        //not so good - 1 is added too many times
        int total3 = integers.stream().reduce(1, (a,b) -> a+b);
        int total4 = integers.parallelStream().reduce(1, (a,b) -> a+b);
        System.out.println(total3);
        System.out.println(total4);

        //this isn't possible as you can't change a non final value in a lambda
//        int counter = 0;
//        integers.parallelStream().forEach( value -> counter++);
//        System.out.println(counter);

        //be careful of non atomic operations
        class HiddenInteger {
            public int count = 0;

        }

        final HiddenInteger hiddenInteger = new HiddenInteger();
        integers.parallelStream().forEach( value -> hiddenInteger.count++);
        System.out.println(hiddenInteger.count);

        //safer version
        class HiddenInteger2 {
            public AtomicInteger count = new AtomicInteger(0);

        }

        final HiddenInteger2 hiddenInteger2 = new HiddenInteger2();
        integers.parallelStream().forEach( value -> hiddenInteger2.count.incrementAndGet());
        System.out.println(hiddenInteger2.count);


    }
}
