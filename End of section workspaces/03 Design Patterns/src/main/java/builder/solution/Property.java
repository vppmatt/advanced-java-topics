package builder.solution;

public class Property {

    public enum PropertyType {
        RESIDENTIAL, COMMERCIAL
    }

    public enum Structure {
        HOUSE, APARTMENT_BLOCK, OFFICE_BLOCK, RETAIL_SHOP, RETAIL_PARK, WAREHOUSE, LAND, OTHER
    }

    public static class Builder {
        private final int id;
        private final String name;
        private PropertyType propertyType = PropertyType.RESIDENTIAL;
        private Structure structure = Structure.HOUSE;
        private int numberOfUnits = 1;

        public Builder(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        public Builder propertyType(final PropertyType propertyType) {
            this.propertyType = propertyType;
            return this;
        }

        public Builder structure(final Structure structure) {
            this.structure = structure;
            return this;
        }

        public Builder numberOfUnits(final int numberOfUnits) {
            this.numberOfUnits = numberOfUnits;
            return this;
        }

        public Property build() {
            return new Property(this);
        }

    }

    private final int id;
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

    private Property(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.propertyType = builder.propertyType;
        this.structure = builder.structure;
        this.numberOfUnits = builder.numberOfUnits;
    }

}
