package mining;

import model.blockchain.HashResult;

import java.util.concurrent.CountDownLatch;

public class ResultsMonitor implements Runnable {

    private HashResult hashResult;

    public ResultsMonitor(HashResult hashResult) {
        this.hashResult = hashResult;
    }



    @Override
    public void run() {
        System.out.println("*** Results monitor - waiting for a valid hashcode to be found");
        try {
            hashResult.isComplete().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*** Results monitor - valid hashcode has been found");

    }
}
