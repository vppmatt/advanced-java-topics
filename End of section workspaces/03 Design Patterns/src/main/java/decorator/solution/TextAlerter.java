package decorator.solution;

public class TextAlerter extends MessageAlerter {

    private MessageAlerter messageAlerter;
    private int phoneNumber;

    public TextAlerter(int phoneNumber, MessageAlerter messageAlerter) {
        this.messageAlerter = messageAlerter;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getMessage() {
        return messageAlerter.getMessage();
    }

    @Override
    public void send() {
        System.out.println("Sending " + getMessage() + " to " + phoneNumber + " by SMS.");
        messageAlerter.send();
    }
}
