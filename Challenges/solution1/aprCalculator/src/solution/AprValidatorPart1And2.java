package solution;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class AprValidatorPart1And2 {

    public static double validate(Loan loan, double testRate) {
        double balance = loan.getPrincipal();
        for (int monthNumber = 0; monthNumber < loan.getTermInMonths(); monthNumber++) {
            LocalDate repaymentDate = loan.getFirstRepayment();
            repaymentDate = repaymentDate.plusMonths(monthNumber);
            long elapsedTimeInDays = ChronoUnit.DAYS.between(loan.getDrawdownDate(), repaymentDate);
            double presentValueRate = elapsedTimeInDays  / 365.0;
            double multiplier = Math.pow(1 + testRate, -presentValueRate);
            double presentValue = multiplier * loan.getRepaymentAmount();
            balance -= presentValue;
        }
        return balance;
    }

    public static Optional<Double> resolve(Loan loan) {

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
                return Optional.empty();
            }
            if (Math.abs(maxRate - minRate) < 0.00001) {
                finished = true;
            }

        }
        return Optional.of(tryRate);
    }
}
