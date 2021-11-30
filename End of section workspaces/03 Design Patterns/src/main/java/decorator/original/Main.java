package decorator.original;

import javax.xml.soap.Text;

public class Main {
    public static void main(String[] args) {

        /* PROBLEM - we have some code that uses the Email Alerter, and some code that uses the text alerter.
        We now want to add in a new alerter type for Slack, and potentially more in the future. We want the
        facility to log when any of these processess start and stop
        */

        EmailAlerter emailAlerter = new EmailAlerter("Server 1703 is not responding", "matt@company.com");
        emailAlerter.send();
        TextAlerter textAlerter = new TextAlerter("Server 1703 is not responding", 12345);
        textAlerter.send();


    }
}
