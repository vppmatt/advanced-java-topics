package factory.original.accounting;

public class WhopAccounts implements AccountingSystem {
    @Override
    public void createInvoice() {
        System.out.println("Whop accounts create invoice");
    }

    @Override
    public void createPurchaseOrder() {
        System.out.println("Whop accounts create purchase order");
    }

    @Override
    public void sendInvoice() {
        System.out.println("Whop accounts send invoice");
    }
}
