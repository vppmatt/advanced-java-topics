package throughputEvaluation;

import java.math.BigInteger;
import java.util.Random;

public class GeneratePrimeNumberTask implements Runnable{


    @Override
    public void run() {
        BigInteger initialValue = new BigInteger(2000, new Random());
        BigInteger prime = initialValue.nextProbablePrime();
        System.out.println(prime);
    }
}
