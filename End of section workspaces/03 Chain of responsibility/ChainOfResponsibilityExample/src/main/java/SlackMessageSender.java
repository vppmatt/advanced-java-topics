public class SlackMessageSender extends MessageSender {


    @Override
    public void send(MessageToSend message) {

        if (message.getSlackId() != null) {
            System.out.println("Sending Slack Message " + message.getMessage() + " to " + message.getSlackId() );
        }
        if(nextSender != null) {
            nextSender.send(message);
        }

    }
}
