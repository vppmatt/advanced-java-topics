import javax.xml.soap.Text;

public class Main {

    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();
        TextMessageSender textMessageSender = new TextMessageSender();
        SlackMessageSender slackMessageSender = new SlackMessageSender();

        MessageSender messageSender = emailSender;
        emailSender.setNextSender(textMessageSender);
        textMessageSender.setNextSender(slackMessageSender);

        MessageToSend messageToSend = new MessageToSend("This is the message");

        messageToSend.setEmail("matt@matt.com");
        messageToSend.setPhoneNumber(12345);

        messageSender.send(messageToSend);
    }
}
