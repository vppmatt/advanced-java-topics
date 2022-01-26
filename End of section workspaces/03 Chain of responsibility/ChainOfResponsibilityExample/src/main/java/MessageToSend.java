public class MessageToSend {

    private String message;
    private Integer phoneNumber;
    private String email;
    private String slackId;

    public MessageToSend(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlackId() {
        return slackId;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
    }
}
