package model.blockchain;

import java.util.concurrent.CountDownLatch;

public class HashResult {
    private int nonce;
    private String hash;
    private boolean complete = false;

    /* part 5 */
    CountDownLatch countDownLatch = new CountDownLatch(1);

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public HashResult() {}

    public int getNonce() {
        return nonce;
    }

    public String getHash() {
        return hash;
    }

    public boolean isComplete() {
        return complete;
    }

    /* start of fix */
    //public void foundAHash(String hash, int nonce) {
    public synchronized void foundAHash(String hash, int nonce) {
        /* end of fix */
        this.hash = hash;
        this.nonce = nonce;
        this.complete = true;
        countDownLatch.countDown();
    }
}
