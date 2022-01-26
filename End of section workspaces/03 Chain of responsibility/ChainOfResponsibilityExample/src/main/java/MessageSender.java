public abstract class MessageSender {
    protected MessageSender nextSender;

    public void setNextSender(MessageSender nextSender) {
        this.nextSender = nextSender;
    }

    public abstract void send(MessageToSend message);

}
