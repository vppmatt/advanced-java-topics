package utilities;

import model.CallStatus;
import model.PhoneCall;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class RandomCallGenerator {

    private static Random random= new Random();
    private static CallStatus[] statuses = new CallStatus[] {CallStatus.ANSWERED, CallStatus.ENGAGED,
            CallStatus.NOT_ANSWERED, CallStatus.FAILED, CallStatus.INVALID_NUMBER};

    public static PhoneCall newCall() {

        CallStatus status = statuses[random.nextInt(statuses.length)];
        int duration = status == CallStatus.ANSWERED ? random.nextInt(5000) : 10;

        return new PhoneCall( random.nextInt(90000), random.nextInt(90000),
                LocalDateTime.now().minus(duration, ChronoUnit.SECONDS), LocalDateTime.now(), status);
    }
}
