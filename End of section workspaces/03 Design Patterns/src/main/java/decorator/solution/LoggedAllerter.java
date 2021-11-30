package decorator.solution;

public class LoggedAllerter extends MessageAlerter {
    private MessageAlerter messageAlerter;

    public LoggedAllerter(MessageAlerter messageAlerter) {
        this.messageAlerter = messageAlerter;
    }

    @Override
    public String getMessage() {
        return messageAlerter.getMessage();
    }

    @Override
    public void send() {
        System.out.println("Logging - starting sending of " + getMessage());
        messageAlerter.send();
        System.out.println("Logging - finished sending of " + getMessage());
    }
}
