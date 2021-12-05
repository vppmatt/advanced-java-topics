package challenge;

import java.text.DecimalFormat;
import java.util.Calendar;

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

    public static void main(String[] args)  {

        Calendar drawdownDate = Calendar.getInstance();
        drawdownDate.set(2019,6,13);
        Calendar firstRepaymentDate1 =   Calendar.getInstance();
        firstRepaymentDate1.set(2019,7,13);
        Calendar firstRepaymentDate2 =   Calendar.getInstance();
        firstRepaymentDate2.set(2019,7,26);

        Loan loan1 = new Loan(1, 10000, drawdownDate.getTime(), firstRepaymentDate1.getTime(), 60, 188.71);
        Loan loan2 = new Loan(1, 10000, drawdownDate.getTime(), firstRepaymentDate2.getTime(), 60, 188.71);

        try {
            Double rate1 = AprValidator.resolve(loan1);
            DecimalFormat df = new DecimalFormat("#.###");
            System.out.println("If payments start on 13th, APR is " + df.format(rate1 * 100) + "%");

            Double rate2 = AprValidator.resolve(loan2);
            System.out.println("If payments start on 20th, APR is " + df.format(rate2 * 100) + "%");
        }
        catch (AprNotResolvedException e) {
            System.out.println("Something went wrong;");
        }
    }

}
