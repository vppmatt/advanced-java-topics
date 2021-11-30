package mining;

import config.Config;
import model.blockchain.Block;
import model.blockchain.HashResult;
import utilities.BlockChainUtils;

public class BlockMiner implements Runnable{

    private Block block;
    private int firstNonce;
    private HashResult hashResult;
    private int difficultyLevel;

    public BlockMiner(Block block, int firstNonce, HashResult hashResult, int difficultyLevel) {
        this.block = block;
        this.firstNonce = firstNonce;
        this.hashResult = hashResult;
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void run() {
        int lastNonce = firstNonce + Config.miningThreadNonceTrials;
        if(firstNonce % 10000000 == 0)
            System.out.println("*** Mining with nonces starting " + firstNonce);
        HashResult hashResult = BlockChainUtils.mineBlock(block, difficultyLevel, firstNonce, lastNonce);
        if (hashResult != null) {
            this.hashResult.foundAHash(hashResult.getHash(), hashResult.getNonce());
        }

    }

}
