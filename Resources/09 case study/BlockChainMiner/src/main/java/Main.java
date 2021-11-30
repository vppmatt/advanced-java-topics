import data.DataContainer;
import incomingCalls.CallSimulator;
import mining.ChainManager;
import mining.MiningSystem;
import restcontrollers.RestServer;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        DataContainer dataContainer = new DataContainer();

        Thread controller= new Thread(new RestServer(dataContainer));
        controller.start();

        Thread callSimulator = new Thread(new CallSimulator(dataContainer));
        callSimulator.start();

        Thread miningSystem = new Thread(new MiningSystem(dataContainer));
        miningSystem.start();

        Thread chainManager = new Thread(new ChainManager(dataContainer));
        chainManager.start();

        controller.join();

    }
}
