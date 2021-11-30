package builder.original;

public class Property {

    public enum PropertyType {
        RESIDENTIAL, COMMERCIAL
    }

    public enum Structure {
        HOUSE, APARTMENT_BLOCK, OFFICE_BLOCK, RETAIL_SHOP, RETAIL_PARK, WAREHOUSE, LAND, OTHER
    }

    private int id;
    private String name;
    private builder.attempt1.Property.PropertyType propertyType;
    private builder.attempt1.Property.Structure structure;
    private int numberOfUnits;

    /*
        REQUIREMENTS:
        1 - Make this an immutable class
        2 - Ensure when the class is instantiated with the id and name. All the other attributes are
            optional. If an attribute is not provided then the default values should be applied:
            Property type: RESIDENTIAL
            Structure: HOUSE
            Number of units: 1
     */
}
