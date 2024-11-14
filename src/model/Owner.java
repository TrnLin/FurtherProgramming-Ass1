package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Owner extends Person {
    private List<Property> ownedProperties;

    // Constructor
    public Owner(String id, String fullName, Date dateOfBirth, String contactInformation) {
        super(id, fullName, dateOfBirth, contactInformation);
        this.ownedProperties = new ArrayList<>();
    }

    public Owner(){
        super();
    }

    public Owner(String trim) {
        super(trim);
    }

    public Owner(String id, String name, String date, String email) {
        super();
    }

    // Getters and Setters
    public List<Property> getOwnedProperties() {
        return ownedProperties;
    }

    public void setOwnedProperties(List<Property> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }
}