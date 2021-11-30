package builder.solution;

public class Main {
    public static void main(String[] args) {
        Property house = new Property.Builder(16, "17 Main Road").build();

        Property head_office = new Property.Builder(17,"Middleton House")
                .propertyType(Property.PropertyType.COMMERCIAL)
                .structure(Property.Structure.OFFICE_BLOCK)
                .numberOfUnits(25)
                .build();

        Property gartonStore = new Property.Builder(18, "Garton Store")
                .propertyType(Property.PropertyType.COMMERCIAL)
                .structure(Property.Structure.RETAIL_SHOP)
                .build();
    }
}
