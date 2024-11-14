package model;

public class ResidentialProperty extends Property {
    private int numberOfBedrooms;
    private boolean hasGarden;
    private boolean isPetFriendly;

    // Constructor
    public ResidentialProperty(String propertyId, String address, double pricing, String status,
                               int numberOfBedrooms, boolean hasGarden, boolean isPetFriendly) {
        super(propertyId, address, pricing, status);
        this.numberOfBedrooms = numberOfBedrooms;
        this.hasGarden = hasGarden;
        this.isPetFriendly = isPetFriendly;
    }

    public ResidentialProperty(String trim) {
        super(trim);
    }

    // Getters and Setters
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    public boolean isPetFriendly() {
        return isPetFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        isPetFriendly = petFriendly;
    }
}