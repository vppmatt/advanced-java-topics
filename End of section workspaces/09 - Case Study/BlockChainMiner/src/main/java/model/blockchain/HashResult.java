package model.blockchain;

import java.util.concurrent.CountDownLatch;

public class HashResult {
    private int nonce;
    private String hash;
    private CountDownLatch complete = new CountDownLatch(1);

    public HashResult() {}

    public int getNonce() {
        return nonce;
    }

    public String getHash() {
        return hash;
    }

    public CountDownLatch isComplete() {
        return complete;
    }

    public void foundAHash(String hash, int nonce) {
        this.hash = hash;
        this.nonce = nonce;
        this.complete.countDown();
    }
}
