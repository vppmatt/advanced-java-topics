package decorator.solution;

public class MessageAlerter {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void send() {
        System.out.println("Message has been sent.");
    }
}
