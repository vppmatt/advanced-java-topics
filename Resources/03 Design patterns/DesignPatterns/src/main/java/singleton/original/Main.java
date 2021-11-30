package singleton.original;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        DataContainer c1 = new DataContainer();
        c1.addIncomingCall(new PhoneCall(123, 456,
                LocalDateTime.of(2021, 06, 15, 14, 22),
                LocalDateTime.of(2021, 06, 15, 14, 39), "ANSWERED"));
        c1.addIncomingCall(new PhoneCall(234, 567,
                LocalDateTime.of(2021, 06, 15, 14, 41),
                LocalDateTime.of(2021, 06, 15, 14, 41), "INVALID_NUMBER"));

        DataContainer c2 = new DataContainer();

        c2.addIncomingCall(new PhoneCall(234, 567,
                LocalDateTime.of(2021, 06, 15, 14, 43),
                LocalDateTime.of(2021, 06, 15, 14, 43), "ENGAGED"));

        System.out.println(c1.equals(c2));
        System.out.println(c2.getIncomingCalls());
    }

}
