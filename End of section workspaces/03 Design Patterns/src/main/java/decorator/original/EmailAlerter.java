package decorator.original;

public class EmailAlerter {
    private String message;
    private String email;

    public EmailAlerter(String message, String email) {
        this.message = message;
        this.email = email;
    }

    public void send() {
        System.out.println("Sending " + message + " to " + email + " by email.");
    }
}
