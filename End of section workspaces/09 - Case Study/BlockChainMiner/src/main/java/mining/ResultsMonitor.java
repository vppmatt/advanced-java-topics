package mining;

import model.blockchain.HashResult;

public class ResultsMonitor implements Runnable {

    private HashResult hashResult;

    public ResultsMonitor(HashResult hashResult) {
        this.hashResult = hashResult;
    }

    @Override
    public void run() {
        System.out.println("*** Results monitor - waiting for a valid hashcode to be found");
//        while (!hashResult.isComplete()) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        if (hashResult.getCountDownLatch().getCount() != 0) {
            try {
                hashResult.getCountDownLatch().await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("*** Results monitor - valid hashcode has been found");
    }
}
