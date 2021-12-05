package solution;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

public class Loan {

    private int id;
    private double principal;
    private LocalDate drawdownDate;
    private LocalDate firstRepayment;
    private int termInMonths;
    private Double repaymentAmount;

    public Loan(int id, double principal, LocalDate drawdownDate, LocalDate firstRepayment, int termInMonths, double repaymentAmount) {
        this.id = id;
        this.principal = principal;
        this.drawdownDate = drawdownDate;
        this.firstRepayment = firstRepayment;
        this.termInMonths = termInMonths;
        this.repaymentAmount = repaymentAmount;
    }

    public int getId() {
        return id;
    }

    public double getPrincipal() {
        return principal;
    }

    public LocalDate getDrawdownDate() {
        return drawdownDate;
    }

    public LocalDate getFirstRepayment() {
        return firstRepayment;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void calculateRate (Function<Loan, Optional<Double>> calculator) {
        DecimalFormat df = new DecimalFormat("#.###");
        Optional<Double> rate = calculator.apply(this);
        if (rate.isPresent()) {
            System.out.println("If payments start on " + getFirstRepayment() + ", APR is " + df.format(rate.get() * 100) + "%");
        }
        else {
            System.out.println("Something went wrong");
        }
    }
}
