package datesAndTimes;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import java.time.*;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

public class DatesAndTimes {

    public static void main(String[] args) {
        //   Create each of the following classes using the .now() method: Instant, LocalDate, LocalTime,
        //   LocalDateTime
        Instant codeStart = Instant.now();
        LocalDate nowLD = LocalDate.now();
        LocalTime nowLT = LocalTime.now();
        LocalDateTime nowLDT = LocalDateTime.now();
        System.out.println("Instant " + codeStart);
        System.out.println("LocalDate " + nowLD);
        System.out.println("LocalTime " + nowLT);
        System.out.println("LocalDateTime " + nowLDT);
        // Use the .format() method of your dates and times to print out the format you wish –
        // this doesn't apply to the Instant class. The way we do this is with this structure:
        // dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"))
        System.out.println(nowLD.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(nowLT.format(DateTimeFormatter.ofPattern("hh:mm")));
        System.out.println(nowLDT.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")));

        // Create a variable called yesterday by taking the LocalDate and subtracting 1 day
        // (hint: look at the method, its parameter types, and use the javadocs to help you!)

        LocalDate yesterday = LocalDate.now().minus(Period.ofDays(1));
        System.out.println("Yesterday " + yesterday);

        //  Create a variable called myBirthday by using the .of() method of LocalDate
        LocalDate myBirthDay = LocalDate.of(1985,7,15);
        System.out.println("My birthday " + myBirthDay);

        //Find out the total number of days there were between your birthday and yesterday
        // (hint: Period.between() won’t work… find out why + research how to do it )


        long days = ChronoUnit.DAYS.between(myBirthDay, nowLD);

        System.out.println("There were " + days + " days between my birthday and today");

        // Find out how many seconds there are between the first instant at the start of the code
        // block, and an instant at the end of your code block.

        Instant codeEnd = Instant.now();
        System.out.println("Elapsed time : " + Duration.between(codeStart,codeEnd).getSeconds()
            + " seconds, " + Duration.between(codeStart,codeEnd).getNano() + " nanoseconds");

    }
}
