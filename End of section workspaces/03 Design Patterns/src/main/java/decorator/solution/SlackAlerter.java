package decorator.solution;

public class SlackAlerter  extends MessageAlerter {
    private MessageAlerter messageAlerter;
    private String slackHandle;

    public SlackAlerter(String slackHandle, MessageAlerter messageAlerter) {
        this.messageAlerter = messageAlerter;
        this.slackHandle = slackHandle;
    }

    @Override
    public String getMessage() {
        return messageAlerter.getMessage();
    }

    @Override
    public void send() {
        System.out.println("Sending " + getMessage() + " to " + slackHandle + " by slack.");
        messageAlerter.send();
    }
}
