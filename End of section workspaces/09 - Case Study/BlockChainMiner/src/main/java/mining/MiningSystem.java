package mining;

import config.Config;
import data.DataContainer;
import model.blockchain.Block;
import model.blockchain.BlockValidationException;
import model.blockchain.HashResult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MiningSystem implements Runnable{

    private DataContainer dataContainer;
    private int difficultyLevel;
    private int miningThreadPoolSize;
    private int miningThreadNonceTrials;
    private int maxNonceStartingValue;

    public static class Builder {
        private int difficultyLevel = 5;
        private int miningThreadPoolSize = 8;
        private final DataContainer dataContainer;
        private int miningThreadNonceTrials = 100000;
        private int maxNonceStartingValue = 10000;

        public Builder(DataContainer dataContainer) {
            this.dataContainer = dataContainer;
        }

        public Builder difficultyLevel(int difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
            return this;
        }

        public Builder miningThreadPoolSize(int miningThreadPoolSize) {
            this.miningThreadPoolSize = miningThreadPoolSize;
            return this;
        }

        public Builder miningThreadNonceTrials(int miningThreadNonceTrials) {
            this.miningThreadNonceTrials= miningThreadNonceTrials;
            return this;
        }

        public Builder maxNonceStartingValue(int maxNonceStartingValue) {
            this.maxNonceStartingValue = maxNonceStartingValue;
            return this;
        }

        public MiningSystem build() {
            return new MiningSystem(dataContainer, difficultyLevel, miningThreadPoolSize,
                    miningThreadNonceTrials, maxNonceStartingValue);
        }

    }

    private MiningSystem(DataContainer dataContainer, int difficultyLevel, int miningThreadPoolSize,
                         int miningThreadNonceTrials,  int maxNonceStartingValue)
    {
        this.dataContainer = dataContainer;
        this.difficultyLevel =difficultyLevel;
        this.miningThreadPoolSize = miningThreadPoolSize;
        this.miningThreadNonceTrials = miningThreadNonceTrials;
        this.maxNonceStartingValue = maxNonceStartingValue;

    }

    @Override
    public void run() {
        String lastHash = "0";

        while (true) {
            Block nextBlock = dataContainer.getActiveBlock();
            if (nextBlock == null) {
                System.out.println("*** Mining system: No blocks to mine... waiting for a block to be created");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("*** Mining system:starting the mining process");
                ExecutorService es =  Executors.newFixedThreadPool(miningThreadPoolSize);
                Long start = System.currentTimeMillis();

                HashResult hashResult = new HashResult();
                Thread resultsThread = new Thread(new ResultsMonitor(hashResult));
                resultsThread.start();

                for (int nonceSeed = 0; nonceSeed < maxNonceStartingValue; nonceSeed++) {
                    BlockMiner miner = new BlockMiner(nextBlock, nonceSeed * miningThreadNonceTrials, hashResult,
                            difficultyLevel, miningThreadNonceTrials);
                    es.execute(miner);

                }

                try {
                    resultsThread.join();
                    es.shutdownNow();
                    if (!hashResult.isComplete()) {
                        throw new RuntimeException("Failed to find a hashcode for block " + dataContainer.getActiveBlock());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /* start of fix */
                synchronized (this) {
                    /* end of fix */

                    nextBlock.setNonce(hashResult.getNonce());
                    nextBlock.setHash(hashResult.getHash());
                    System.out.println("*** Mining system: successfully mined a block");

                    System.out.println("Block " + nextBlock + " hash : " + nextBlock.getHash());
                    System.out.println("Block " + nextBlock + " nonce: " + nextBlock.getNonce());
                }
                Long end = System.currentTimeMillis();

                System.out.println("Time taken " + (end - start) + " ms.");

                //sleeping for 10 seconds to allow for the chain manager to receive the mined block and
                //set up the next one
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}
