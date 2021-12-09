package data;

import model.PhoneCall;
import model.blockchain.Block;
import model.blockchain.BlockChain;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DataContainer {

    private static DataContainer instance;

    public static DataContainer getInstance() {

        if (instance == null) {
            instance = new DataContainer();
        }
        return instance;

    }

    private DataContainer() {}

    /*start of fix*/
    //private List<PhoneCall> incomingCalls = new ArrayList<>();
    private Queue<PhoneCall> incomingCalls = new ConcurrentLinkedQueue<>();
    /*end of fix*/

    private BlockChain blockChain = new BlockChain.Builder().build();
    private Block activeBlock;

    /*start of fix*/
    //private int nextTransactionId = 0;
    private AtomicInteger nextTransactionId = new AtomicInteger(0);
    /*end of fix*/


    public Queue<PhoneCall> getIncomingCalls() {
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
        /* start of fix */
        //incomingCall.setId(nextTransactionId++);
        incomingCall.setId(nextTransactionId.incrementAndGet());
        /* end of fix */
        incomingCalls.add(incomingCall);
    }
}
