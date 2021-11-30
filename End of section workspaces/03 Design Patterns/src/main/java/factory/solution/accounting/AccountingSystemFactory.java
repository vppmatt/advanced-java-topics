package factory.solution.accounting;

import factory.solution.domain.Organisation;

public class AccountingSystemFactory {

    public static AccountingSystem getAccountingSystem(Organisation.OrganisationType type) {
        if (type == Organisation.OrganisationType.COMPANY) {
            return new RetinaAccounts();
        }
        else if(type == Organisation.OrganisationType.SME) {
            return new ThymeAccounts();
        }
        else {
            return new WhopAccounts();
        }
    }
}
