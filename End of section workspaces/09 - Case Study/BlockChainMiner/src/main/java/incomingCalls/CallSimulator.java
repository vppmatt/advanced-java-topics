package incomingCalls;

import config.Config;
import data.DataContainer;
import model.PhoneCall;
import utilities.RandomCallGenerator;

import java.util.Random;

public class CallSimulator implements Runnable{

    private DataContainer dataContainer;
    private Random random = new Random();

    public CallSimulator(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public void run() {
        System.out.println("*** call simulator thread is running");
        while(true) {
            try {
                Thread.sleep(random.nextInt(Config.callGenerationTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PhoneCall newCall = RandomCallGenerator.newCall();
            dataContainer.addIncomingCall(newCall);
        }
    }
}
