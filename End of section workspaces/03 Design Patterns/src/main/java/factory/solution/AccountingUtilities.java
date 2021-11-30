package factory.solution;

import factory.solution.accounting.*;
import factory.solution.domain.Organisation;

public class AccountingUtilities {
    public void initiateOrder(String product, double amount, Organisation organisation) {
        AccountingSystem accountingSystem = AccountingSystemFactory.getAccountingSystem(organisation.getType());
        accountingSystem.createPurchaseOrder();
    }

    public void fulfilOrder(int orderId, Organisation organisation) {
        AccountingSystem accountingSystem = AccountingSystemFactory.getAccountingSystem(organisation.getType());
        accountingSystem.createInvoice();
        accountingSystem.sendInvoice();
    }
}
