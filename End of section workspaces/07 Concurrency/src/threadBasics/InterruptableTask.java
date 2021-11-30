package threadBasics;

public class InterruptableTask implements Runnable {


    @Override
    public void run() {

        boolean iHaveBeenToldToStop = false;

        //Version 1 - using Thread.sleep
//        while(!iHaveBeenToldToStop) {
//            System.out.println("The interruptable task is running");
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                iHaveBeenToldToStop = true;
//            }
//        }

        //Version 2 - with a non sleeping thread
        while(!iHaveBeenToldToStop) {
            System.out.println("The interruptable task is running");
            if (Thread.currentThread().isInterrupted()) {
                iHaveBeenToldToStop = true;
            }

        }
    }
}
