package singleton.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataContainer {

    private static DataContainer instance;

    public static DataContainer getInstance() {
        if (instance == null) {
            instance = new DataContainer();
        }
        return instance;
    }


    private DataContainer() {
    }

    private List<PhoneCall> incomingCalls = new ArrayList<>();
    private int nextTransactionId = 0;

    public List<PhoneCall> getIncomingCalls() {
        return incomingCalls;
    }

    public void addIncomingCall(PhoneCall incomingCall) {
        incomingCall.setId(nextTransactionId++);
        incomingCalls.add(incomingCall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataContainer that = (DataContainer) o;
        return nextTransactionId == that.nextTransactionId && Objects.equals(incomingCalls, that.incomingCalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incomingCalls, nextTransactionId);
    }
}
