package decorator.solution;

public class Main {

    public static void main(String[] args) {

        MessageAlerter messageAlerter = new MessageAlerter();
        messageAlerter.setMessage("Server 1703 is not responding");

        MessageAlerter alerter =
                new LoggedAllerter(
                        new TextAlerter(12345,
                                new EmailAlerter("matt@company.com",
                                        new SlackAlerter("@matt", messageAlerter))
                        )
                );

        alerter.send();
    }

}
