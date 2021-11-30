package decorator.solution;

public class EmailAlerter extends MessageAlerter {

    private MessageAlerter messageAlerter;
    private String email;

    public EmailAlerter(String email, MessageAlerter messageAlerter) {
        this.messageAlerter = messageAlerter;
        this.email = email;
    }

    @Override
    public String getMessage() {
        return messageAlerter.getMessage();
    }

    @Override
    public void send() {
        System.out.println("Sending " + getMessage() + " to " + email + " by email.");
        messageAlerter.send();
    }

}
