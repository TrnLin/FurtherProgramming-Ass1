/** 
* @author Tran Hoang Linh - S03097 
*/ 

package model;
import java.util.Date;

public class Person {
    private String id;
    private String fullName;
    private Date dateOfBirth;
    private String contactInformation;

    // Constructor
    public Person(String id, String fullName, Date dateOfBirth, String contactInformation) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactInformation = contactInformation;
    }

    public Person(){
        this.id = "";
        this.fullName = "";
        this.dateOfBirth = new Date();
        this.contactInformation = "";
    }

    public Person(String trim) {
        this.id = trim;
        this.fullName = trim;
        this.dateOfBirth = new Date();
        this.contactInformation = trim;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
}