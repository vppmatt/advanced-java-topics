package data;

import model.PhoneCall;
import model.blockchain.Block;
import model.blockchain.BlockChain;

import java.util.ArrayList;
import java.util.List;

public class DataContainer {

    private List<PhoneCall> incomingCalls = new ArrayList<>();
    private BlockChain blockChain = new BlockChain();
    private Block activeBlock;
    private int nextTransactionId = 0;

    public List<PhoneCall> getIncomingCalls() {
        return incomingCalls;
    }

    public BlockChain getBlockChain() {
        return blockChain;
    }

    public Block getActiveBlock() {
        return activeBlock;
    }

    public void setActiveBlock(Block activeBlock) {
        this.activeBlock = activeBlock;
    }

    public void addIncomingCall(PhoneCall incomingCall) {
        incomingCall.setId(nextTransactionId++);
        incomingCalls.add(incomingCall);
    }
}
