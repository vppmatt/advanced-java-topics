package factory.original.domain;



public class Organisation {

    public enum OrganisationType {
        SME, COMPANY, CONGLOMERATE
    }

    public Organisation(int id, String name, OrganisationType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    private int id;
    private String name;
    private OrganisationType type;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OrganisationType getType() {
        return type;
    }

}
