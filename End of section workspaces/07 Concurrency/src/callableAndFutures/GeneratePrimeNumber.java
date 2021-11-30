package callableAndFutures;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.Callable;

public class GeneratePrimeNumber implements Callable<BigInteger> {
    @Override
    public BigInteger call() throws Exception {
        BigInteger initialValue = new BigInteger(2000, new Random());
        BigInteger prime = initialValue.nextProbablePrime();
        return prime;
    }
}
