package builder.attempt1;

public class Property {

    /*
        REQUIREMENTS:
        1 - Avoid the telescoping constructors
        2 - Make the class easier to construct (a fluent API)
     */

    public enum PropertyType {
        RESIDENTIAL, COMMERCIAL
    }

    public enum Structure {
        HOUSE, APARTMENT_BLOCK, OFFICE_BLOCK, RETAIL_SHOP, RETAIL_PARK, WAREHOUSE, LAND, OTHER
    }

    private final  int id;
    private final String name;
    private final PropertyType propertyType;
    private final Structure structure;
    private final int numberOfUnits;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public Structure getStructure() {
        return structure;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public Property(int id, String name, PropertyType propertyType, Structure structure, int numberOfUnits) {
        this.id = id;
        this.name = name;
        this.propertyType = propertyType;
        this.structure = structure;
        this.numberOfUnits = numberOfUnits;
    }

    public Property(int id, String name, PropertyType propertyType, Structure structure) {
        this(id, name, propertyType, structure, 1);
    }

    public Property(int id, String name, Structure structure, int numberOfUnits) {
        this(id, name, PropertyType.RESIDENTIAL, structure, 1);
    }

    public Property(int id, String name, PropertyType propertyType, int numberOfUnits) {
        this(id, name, propertyType, Structure.HOUSE, 1);
    }

    public Property(int id, String name, PropertyType propertyType) {
        this(id,name,propertyType,Structure.HOUSE,1);
    }

    public Property(int id, String name, Structure structure) {
        this(id,name,PropertyType.RESIDENTIAL,structure, 1);
    }

    public Property(int id, String name, int numberOfUnits) {
        this(id, name, PropertyType.RESIDENTIAL, Structure.HOUSE, numberOfUnits);
    }

    public Property(int id, String name) {
        this(id,name,PropertyType.RESIDENTIAL, Structure.HOUSE, 1);
    }



}
