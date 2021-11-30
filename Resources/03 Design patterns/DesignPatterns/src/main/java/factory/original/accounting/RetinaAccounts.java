package factory.original.accounting;

public class RetinaAccounts implements AccountingSystem {

    @Override
    public void createInvoice() {
        System.out.println("Retina create invoice");
    }

    @Override
    public void createPurchaseOrder() {
        System.out.println("Retina create purchase order");
    }

    @Override
    public void sendInvoice() {
        System.out.println("Retina send invoice");
    }
}
