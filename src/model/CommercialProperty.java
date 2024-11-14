package model;

public class CommercialProperty extends Property {
    private String businessType;
    private int parkingSpaces;
    private double squareFootage;

    // Constructor
    public CommercialProperty(String propertyId, String address, double pricing, String status,
                              String businessType, int parkingSpaces, double squareFootage) {
        super(propertyId, address, pricing, status);
        this.businessType = businessType;
        this.parkingSpaces = parkingSpaces;
        this.squareFootage = squareFootage;
    }

    // Getters and Setters
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public double getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(double squareFootage) {
        this.squareFootage = squareFootage;
    }
}