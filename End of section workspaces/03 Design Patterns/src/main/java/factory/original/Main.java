package factory.original;

import factory.original.domain.Database;
import factory.original.domain.Organisation;

public class Main {
    public static void main(String[] args) {

        int currentOrganisationId = 322;

        Organisation organisation = Database.retrieveOrganisation(currentOrganisationId);

        AccountingUtilities accountingUtilities = new AccountingUtilities();
        accountingUtilities.initiateOrder("Some product", 552.33, organisation);
        accountingUtilities.fulfilOrder(42, organisation);

    }
}
