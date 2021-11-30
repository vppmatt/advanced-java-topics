package builder.original;

public class Main {
    public static void main(String[] args) {
        Property house = new Property(16, "17 Main Road");

        Property head_office = new Property(17,"Middleton House",
                Property.PropertyType.COMMERCIAL,
                Property.Structure.OFFICE_BLOCK,
                25);

        Property gartonStore = new Property(18, "Garton Store",
                Property.PropertyType.COMMERCIAL,
                Property.Structure.RETAIL_SHOP);
    }
}
