package interfaces;

import java.time.LocalDate;

public class CurrentAccount implements BankAccount {

    String accountName;
    LocalDate openDate;
    double currentBalance;

    @Override
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public void setOpenDate(LocalDate date) {
        this.openDate=date;
    }

    @Override
    public void addFunds(double amount) {
        currentBalance += amount;
    }

    @Override
    public double getBalance() {
        return currentBalance;
    }
}
