package challenge;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class AprValidator {

    public static double validate(Loan loan, double testRate) {
        double balance = loan.getPrincipal();
        for (int monthNumber = 0; monthNumber < loan.getTermInMonths(); monthNumber++) {
            Calendar repaymentDate = Calendar.getInstance();
            repaymentDate.setTime(loan.getFirstRepayment());
            repaymentDate.add(Calendar.MONTH, monthNumber);
            long elapsedTimeInMS = repaymentDate.getTime().getTime() - loan.getDrawdownDate().getTime();
            long elapsedTimeInDays = TimeUnit.MILLISECONDS.toDays(elapsedTimeInMS);
            double presentValueRate = elapsedTimeInDays / 365.0;
            double multiplier = Math.pow(1 + testRate, -presentValueRate);
            double presentValue = multiplier * loan.getRepaymentAmount();
            balance -= presentValue;

        }
        return balance;
    }

    public static double resolve(Loan loan) throws AprNotResolvedException {

        double minRate = 0.01;
        double maxRate = 1.0;
        double tryRate = 0.5;

        boolean finished = false;
        int maxIterations = 100;


        while (!finished) {
            tryRate = (maxRate - minRate) / 2 + minRate;
            double tryValue = validate(loan, tryRate);
            if (tryValue > 0) {
                maxRate = tryRate;
            } else {
                minRate = tryRate;
            }
            maxIterations--;
            if (maxIterations < 0) {
                throw new AprNotResolvedException();
            }
            if (Math.abs(maxRate - minRate) < 0.00001) {
                finished = true;
            }

        }
        return tryRate;
    }
}

