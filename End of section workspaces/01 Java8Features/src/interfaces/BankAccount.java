package interfaces;

import java.time.LocalDate;

public interface BankAccount {

    public void setAccountName(String accountName);
    public void setOpenDate(LocalDate date);
    public void addFunds(double amount);
    public double getBalance();

    public default void suspendAccount() {
        throw new RuntimeException("function not available");
    }

    public static String getVersion() {
        return "v1";
    }

}
