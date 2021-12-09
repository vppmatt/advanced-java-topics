import data.DataContainer;
import incomingCalls.CallSimulator;
import mining.ChainManager;
import mining.ChainManagerFactory;
import mining.MiningSystem;
import restcontrollers.RestServer;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        DataContainer dataContainer = DataContainer.getInstance();

        Thread controller= new Thread(new RestServer(dataContainer));
        controller.start();

        Thread callSimulator = new Thread(new CallSimulator(dataContainer));
        callSimulator.start();

        Thread miningSystem = new Thread(new MiningSystem.Builder(dataContainer).build());
        miningSystem.start();

        Thread chainManager = new Thread(ChainManagerFactory.getChainManager());
        chainManager.start();

        controller.join();

    }
}
