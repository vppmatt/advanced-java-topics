package mining;

import config.Config;
import data.DataContainer;
import model.blockchain.Block;
import model.blockchain.BlockValidationException;
import model.blockchain.HashResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MiningSystem implements Runnable{

    private DataContainer dataContainer;

    public MiningSystem(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
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
                ExecutorService es =  Executors.newFixedThreadPool(Config.miningThreadPoolSize);
                Long start = System.currentTimeMillis();
                HashResult hashResult = new HashResult();
                Thread resultsThread = new Thread(new ResultsMonitor(hashResult));
                resultsThread.start();

                for (int nonceSeed = 0; nonceSeed < Config.maxNonceStartingValue; nonceSeed++) {
                    BlockMiner miner = new BlockMiner(nextBlock, nonceSeed * Config.miningThreadNonceTrials, hashResult, Config.difficultyLevel);
                    es.execute(miner);

                }

                try {

                    resultsThread.join();
                    es.shutdownNow();
                    if (hashResult.isComplete().getCount() != 0) {
                        throw new RuntimeException("Failed to find a hashcode for block " + dataContainer.getActiveBlock());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                nextBlock.setNonce(hashResult.getNonce());
                nextBlock.setHash(hashResult.getHash());
                System.out.println("*** Mining system: successfully mined a block");

                System.out.println("Block " + nextBlock + " hash : " + nextBlock.getHash());
                System.out.println("Block " + nextBlock + " nonce: " + nextBlock.getNonce());

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
