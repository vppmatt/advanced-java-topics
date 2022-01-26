package challenge;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    /*
    Challenge:
    This is the aprCalculator appliation we used for the previous challenge. In this version
    the AprValidator is using regular java methods (previously it was static methods or lambdas).

    (1) Add dependencies to the project for myBatis, h2, junit and mockito.

    (2) Create a new class called PersistedAprValidator which takes the APRValidator, and if the result is present,
     saves the data to an H2 database. Create it using the Decorator pattern. Convert the main method to use this
     new class.

     To help you do this in the solution package you will find:
     * DBUtils - this is a class which takes care of all the database work. Instantiate a copy from the Main method,
     and insert it into your PersistedAprValidator class using dependency injection.

     * LoanRate - this is the entity which will be saved in the database

     * LoanRateMapper - this will let MyBatis work with the data types.

     To be clear - your PersistedAprValidator class just needs to call the DBUtils' save method if the rate
     has been calculated, passing in the id of the loan and the calculated rate.

     (3)  Create a unit test that ensures that the PersistedAprCalculator calculates one of the values correctly
     (use one of the parameters in the main method as the basis for the test).

     Hint: to do this you will need to use mocks, and possibly a spy

     (4) At the end of the main method is a list of loans. Find the APR rate for each one, and then
     print out the average of the highest and lowest rate. Use streams where possible.

     And finally... Call the printAllSavedLoanRates method in DBUtils to print out all the
     saved data so that we know it worked.

    */

    public static void main(String[] args) {

        LocalDate drawdownDate = LocalDate.of(2019,7,13);
        LocalDate firstRepaymentDate1 = LocalDate.of(2019,8,13);
        LocalDate firstRepaymentDate2 =  LocalDate.of(2019,8,26);

        Loan loan1 = new Loan(1, 10000, drawdownDate, firstRepaymentDate1, 60, 188.71);
        Loan loan2 = new Loan(2, 10000, drawdownDate, firstRepaymentDate2, 60, 188.71);

        AprValidator aprValidator = new AprValidator();

        Optional<Double> rate1 = aprValidator.resolve(loan1);
        Optional<Double> rate2 = aprValidator.resolve(loan2);

        DecimalFormat df = new DecimalFormat("#.###");
        if (rate1.isPresent()) System.out.println("Rate 1 " + df.format(rate1.get() * 100) + "%");
        if (rate2.isPresent()) System.out.println("Rate 2 " + df.format(rate2.get() * 100) + "%");


        List<Loan> loansList = new ArrayList<>();
        loansList.add(new Loan(1, 10000, drawdownDate, firstRepaymentDate1, 60, 188.71));
        loansList.add(new Loan(2, 12000, drawdownDate, firstRepaymentDate1, 60, 204.19));
        loansList.add(new Loan(3, 15000, drawdownDate, firstRepaymentDate1, 60, 222.36));
        loansList.add(new Loan(4, 15000, drawdownDate, firstRepaymentDate1, 48, 281.00));
        loansList.add(new Loan(5, 10000, drawdownDate, firstRepaymentDate1, 60, 193.36));
        loansList.add(new Loan(6, 10000, drawdownDate, firstRepaymentDate1, 60, 181.60));
        loansList.add(new Loan(7, 10000, drawdownDate, firstRepaymentDate1, 60, 190.50));
        loansList.add(new Loan(8, 10000, drawdownDate, firstRepaymentDate1, 60, 191.50));
        loansList.add(new Loan(9, 10000, drawdownDate, firstRepaymentDate1, 60, 192.50));
        loansList.add(new Loan(10, 10000, drawdownDate, firstRepaymentDate1, 60, 193.50));

    }

}
