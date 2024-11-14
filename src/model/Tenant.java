package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tenant extends Person {
    private List<RentalAgreement> rentalAgreements;
    private List<Payment> paymentRecords;

    // Constructor    
    public Tenant(String id, String fullName, Date dateOfBirth, String contactInformation) {
        super(id, fullName, dateOfBirth, contactInformation);
        this.rentalAgreements = new ArrayList<>();
        this.paymentRecords = new ArrayList<>();
    }

    public Tenant(String trim) {
        super(trim);
    }

    // Getters and Setters    
    public List<RentalAgreement> getRentalAgreements() {
        return rentalAgreements;
    }

    public void setRentalAgreements(List<RentalAgreement> rentalAgreements) {
        this.rentalAgreements = rentalAgreements;
    }

    public List<Payment> getPaymentRecords() {
        return paymentRecords;
    }

    public void setPaymentRecords(List<Payment> paymentRecords) {
        this.paymentRecords = paymentRecords;
    }
}