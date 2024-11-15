/** 
* @author Tran Hoang Linh - S03097 
*/ 

package model;

public class Property {
    private String propertyId;
    private String address;
    private double pricing;
    private String status;

    // Constructor
    public Property(String propertyId, String address, double pricing, String status) {
        this.propertyId = propertyId;
        this.address = address;
        this.pricing = pricing;
        this.status = status;
    }

    // Constructor with default values
    public Property(String propertyId) {
        this.propertyId = null;
        this.address = propertyId;
        this.pricing = 0.0;
        this.status = "";
    }

    // Getters and Setters
    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPricing() {
        return pricing;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}