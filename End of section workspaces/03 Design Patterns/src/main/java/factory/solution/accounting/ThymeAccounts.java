package factory.solution.accounting;

public class ThymeAccounts implements AccountingSystem{

    protected ThymeAccounts() {}

    @Override
    public void createInvoice() {
        System.out.println("Thyme create invoice");
    }

    @Override
    public void createPurchaseOrder() {
        System.out.println("Thyme create purchase order");
    }

    @Override
    public void sendInvoice() {
        System.out.println("Thyme send invoice");
    }
}
