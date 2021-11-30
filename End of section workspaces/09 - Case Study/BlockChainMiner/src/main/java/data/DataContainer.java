package data;

import model.PhoneCall;
import model.blockchain.Block;
import model.blockchain.BlockChain;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataContainer {

    //private List<PhoneCall> incomingCalls = new ArrayList<>();
    private Queue<PhoneCall> incomingCalls = new ConcurrentLinkedQueue<>();
    private BlockChain blockChain = new BlockChain();
    private Block activeBlock;

    //private int nextTransactionId = 0;
    private AtomicInteger nextTransactionId = new AtomicInteger(0);

    private Lock getIncomingCallsLock = new ReentrantLock();

    //this is now for use by the rest controller only - i.e. as a readonly
    public Queue<PhoneCall> getIncomingCalls() {
        return incomingCalls;
    }

    //new method to safely remove a set of incoming calls for the next block to use
    public List<PhoneCall> pullIncomingCalls() {
        ArrayList<PhoneCall> calls = new ArrayList(incomingCalls);
        incomingCalls.removeAll(calls);
        return calls;
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
        incomingCall.setId(nextTransactionId.getAndIncrement());
        incomingCalls.add(incomingCall);
    }
}
