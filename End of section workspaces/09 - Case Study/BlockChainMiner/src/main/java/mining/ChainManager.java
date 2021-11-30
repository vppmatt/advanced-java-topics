package mining;

import data.DataContainer;
import model.PhoneCall;
import model.blockchain.Block;
import model.blockchain.BlockValidationException;

import java.util.ArrayList;
import java.util.List;

public class ChainManager implements Runnable{

    private DataContainer dataContainer;

    public ChainManager(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public void run() {
        while(true) {
            if (dataContainer.getActiveBlock() == null) {
                System.out.println("*** Chain Manager: there is no active block");
                List<PhoneCall> phoneCalls =dataContainer.pullIncomingCalls();
                if (phoneCalls.size() == 0) {
                    System.out.println("*** Chain Manager: No transactions waiting to be mined...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Block block = new Block(dataContainer.getBlockChain().getSize(), phoneCalls, dataContainer.getBlockChain().getLastHash());
                    dataContainer.getIncomingCalls().removeAll(phoneCalls);
                    dataContainer.setActiveBlock(block);
                    System.out.println("*** Chain Manager: created new block with lastHash " + block.getPreviousHash() );
                }
            }
            else if(dataContainer.getActiveBlock().getHash() != null) {
                System.out.println("*** Chain Manager: active block has been mined");
                try {
                    dataContainer.getBlockChain().addBlock(dataContainer.getActiveBlock());
                    dataContainer.getBlockChain().printAndValidate();
                    dataContainer.setActiveBlock(null);
                    System.out.println("*** Chain Manager: mined block has been added to blockchain");

                } catch (BlockValidationException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                //System.out.println("*** Chain Manager: nothing to do - mining is underway ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
