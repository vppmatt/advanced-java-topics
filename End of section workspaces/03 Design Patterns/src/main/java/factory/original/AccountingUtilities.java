package factory.original;

import factory.original.accounting.AccountingSystem;
import factory.original.accounting.RetinaAccounts;
import factory.original.accounting.ThymeAccounts;
import factory.original.accounting.WhopAccounts;
import factory.original.domain.Organisation;

public class AccountingUtilities {
    public void initiateOrder(String product, double amount, Organisation organisation) {
        AccountingSystem accountingSystem = null;

        if (organisation.getType() == Organisation.OrganisationType.COMPANY) {
            accountingSystem = new RetinaAccounts();
        }
        else if(organisation.getType() == Organisation.OrganisationType.SME) {
            accountingSystem = new ThymeAccounts();
        }
        else {
            accountingSystem = new WhopAccounts();
        }

        accountingSystem.createPurchaseOrder();

    }

    public void fulfilOrder(int orderId, Organisation organisation) {
        AccountingSystem accountingSystem = null;

        if (organisation.getType() == Organisation.OrganisationType.COMPANY) {
            accountingSystem = new RetinaAccounts();
        }
        else if(organisation.getType() == Organisation.OrganisationType.SME) {
            accountingSystem = new ThymeAccounts();
        }
        else {
            accountingSystem = new WhopAccounts();
        }

        accountingSystem.createInvoice();
        accountingSystem.sendInvoice();
    }
}
