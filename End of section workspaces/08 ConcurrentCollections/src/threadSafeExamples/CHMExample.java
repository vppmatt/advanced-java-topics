package threadSafeExamples;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CHMExample {

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, UUID> map = new HashMap<>();
        //ConcurrentMap<Integer, UUID> map = new ConcurrentHashMap<>();

            for (int i = 0; i < 1000; i++) {
                map.put(i, UUID.randomUUID());
                System.out.println(map.size());
            }

        Thread highest = new Thread(() -> {
            while (map.size() > 0) {
                    UUID uuid = map.values().stream().max( (a, b) -> a.compareTo(b)).get();
                    System.out.println("Highest : " + uuid);
                }
        });

        Thread removing = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Taken : " + map.remove(i) + "(" + i + ")");

            }
        });

        highest.start();
        removing.start();
        highest.join();
        removing.join();



    }
}
