package config;

import java.util.Locale;

public class Config {

    public static final int difficultyLevel = 6;
    public static final int miningThreadPoolSize = 10;
    public static final Locale locale = Locale.UK;
    public static final int callGenerationTime = 2000;
    public static final int miningThreadNonceTrials = 100000; //each miner will try 100,000 nonces
    public static final int maxNonceStartingValue = 10000;
    //highest nonce to try = 10,000 * 100,000 + 100,000 = 1,000,100,000 (just over 1 trillion)
}
