package interfaces;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        BankAccount account1 = new CurrentAccount();
        account1.setAccountName("William Partridge");
        account1.setOpenDate(LocalDate.now());
        account1.addFunds(0);

        System.out.println(BankAccount.getVersion());

    }
}
