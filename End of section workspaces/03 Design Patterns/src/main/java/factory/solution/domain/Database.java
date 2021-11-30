package factory.solution.domain;

public class Database {

    public static Organisation retrieveOrganisation(int organisationId) {

        switch(organisationId) {
            case 106: return new Organisation(106, "Small co", Organisation.OrganisationType.SME);
            case 322: return new Organisation(322, "Large co", Organisation.OrganisationType.COMPANY);
            case 655: return new Organisation(655, "Multinational", Organisation.OrganisationType.CONGLOMERATE);
            default:
                throw new RuntimeException("Unknown organisation");
        }
    }
}
