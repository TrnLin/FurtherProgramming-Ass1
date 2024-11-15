package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Host extends Person {
    private List<Property> managedProperties;

    // Constructor
    public Host(String id, String name, Date dateOfBirth, String contactInfo) {
        super(id, name, dateOfBirth, contactInfo);
        this.managedProperties = new ArrayList<>();
    }
    public Host(){
        super();
    }

    public Host(String trim) {
        super(trim);
    }

    public Host(String s, String name, String date, String email) {
        super();
    }




    // Getters and Setters
    public List<Property> getManagedProperties() {
        return managedProperties;
    }

    public void setManagedProperties(List<Property> managedProperties) {
        this.managedProperties = managedProperties;
    }
}