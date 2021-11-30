package singleton.original;

import java.time.LocalDateTime;
import java.util.Objects;

public class PhoneCall {
    private Integer id;
    private Integer caller;
    private Integer recipient;
    private LocalDateTime start;
    private LocalDateTime end;
    private String status;

    public PhoneCall(Integer caller, Integer recipient, LocalDateTime start, LocalDateTime end, String status) {
        this.caller = caller;
        this.recipient = recipient;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public Integer getCaller() {
        return caller;
    }

    public void setCaller(Integer caller) {
        this.caller = caller;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneCall phoneCall = (PhoneCall) o;
        return Objects.equals(id, phoneCall.id) && Objects.equals(caller, phoneCall.caller) && Objects.equals(recipient, phoneCall.recipient) && Objects.equals(start, phoneCall.start) && Objects.equals(end, phoneCall.end) && status == phoneCall.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caller, recipient, start, end, status);
    }

    @Override
    public String toString() {
        return "PhoneCall{" +
                "id=" + id +
                ", caller=" + caller +
                ", recipient=" + recipient +
                ", start=" + start +
                ", end=" + end +
                ", status=" + status +
                '}';
    }
}
