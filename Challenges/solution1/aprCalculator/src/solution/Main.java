package solution;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;

public class Main {

    /*
    Challenge:
    (1) Replace any references to Calendar + Date with Java8 Date and Time objects
    (2) Amend the AprValidator so that it returns an optional of a rate, and does not throw an exception
    (3) Re-architect the code to use Lambdas as follows:
    - Make the AprValidator into a lambda - it should be an example of a calculator, and we will have
      other potential calculators that we can swap with it later on. (Hint: make the class implement a Functional
      Interface)
    - the Loan class should contain a new method called calculateRate. This method should take
      a parameter which has a functional interface data type (i.e. we can pass either our AprCalculator or
      some other lambda into this method)
    - The calculateRate method should run the calculation and then print out the results - it does not need
      to return anything
    - Rather than running the calculations from the main method, simply call each loan's calculateRate method,
      passing in an instance of the AprCalculator lambda.

    */

    public static void main(String[] args) {

        LocalDate drawdownDate = LocalDate.of(2019,7,13);
        LocalDate firstRepaymentDate1 = LocalDate.of(2019,8,13);
        LocalDate firstRepaymentDate2 =  LocalDate.of(2019,8,26);

        Loan loan1 = new Loan(1, 10000, drawdownDate, firstRepaymentDate1, 60, 188.71);
        Loan loan2 = new Loan(1, 10000, drawdownDate, firstRepaymentDate2, 60, 188.71);

        AprValidator calculator = new AprValidator();
        loan1.calculateRate(calculator);
        loan2.calculateRate(calculator);

    }

}
