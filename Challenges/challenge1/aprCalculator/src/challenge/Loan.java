package challenge;

import java.util.Date;

public class Loan {

    private int id;
    private double principal;
    private Date drawdownDate;
    private Date firstRepayment;
    private int termInMonths;
    private Double repaymentAmount;

    public Loan(int id, double principal, Date drawdownDate, Date firstRepayment, int termInMonths, double repaymentAmount) {
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

    public Date getDrawdownDate() {
        return drawdownDate;
    }

    public Date getFirstRepayment() {
        return firstRepayment;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public double getRepaymentAmount() {
        return repaymentAmount;
    }
}
