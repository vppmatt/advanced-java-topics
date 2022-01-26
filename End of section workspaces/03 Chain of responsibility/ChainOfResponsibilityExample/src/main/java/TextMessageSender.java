

public class TextMessageSender extends MessageSender {


    @Override
    public void send(MessageToSend message) {
        if (message.getPhoneNumber() != null) {
            System.out.println("Sending Text Message " + message.getMessage() + " to " + message.getPhoneNumber());
        }
        if (nextSender != null) {
            nextSender.send(message);
        }
    }
}
