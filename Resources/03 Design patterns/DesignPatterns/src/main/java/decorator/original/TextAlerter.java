package decorator.original;

public class TextAlerter {
    private String message;
    private int phoneNumber;

    public TextAlerter(String message, int phoneNumber) {
        this.message = message;
        this.phoneNumber = phoneNumber;
    }

    public void send() {
        System.out.println("Sending " + message + " to " + phoneNumber + " by SMS.");
    }
}
