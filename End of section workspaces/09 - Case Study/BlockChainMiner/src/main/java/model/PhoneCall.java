package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class PhoneCall {
    private Integer id;
    private Integer caller;
    private Integer recipient;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime start;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime end;

    private CallStatus status;

    public PhoneCall(Integer caller, Integer recipient, LocalDateTime start, LocalDateTime end, CallStatus status) {
        this.caller = caller;
        this.recipient = recipient;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public Integer getCaller() {
        return caller;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public CallStatus getStatus() {
        return status;
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
