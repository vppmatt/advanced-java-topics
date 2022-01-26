public class EmailSender extends MessageSender {


    @Override
    public void send(MessageToSend message) {

        if (message.getEmail() != null) {
            System.out.println("Sending Email Message " + message.getMessage() + " to " + message.getEmail() );
        }
        if(nextSender != null) {
            nextSender.send(message);
        }

    }
}
