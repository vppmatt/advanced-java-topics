package challenge;

import java.time.LocalDate;

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

}
