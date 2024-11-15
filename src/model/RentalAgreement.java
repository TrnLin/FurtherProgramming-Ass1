/** 
* @author Tran Hoang Linh - S03097 
*/ 

package model;

import java.util.Date;
import java.util.List;

public class RentalAgreement {
    private String agreementId;
    private List<Tenant> tenants;
    private List<Host> hosts;
    private Owner owner;
    private Property property;
    private Date contractDate;
    private String period;
    private double rentingFee;
    private String status;

    // Constructor
    public RentalAgreement(String agreementId, List<Tenant> tenants, List<Host> hosts, Owner owner, 
                           Property property, Date contractDate, String period, double rentingFee, String status) {
        this.agreementId = agreementId;
        this.tenants = tenants;
        this.hosts = hosts;
        this.owner = owner;
        this.property = property;
        this.contractDate = contractDate;
        this.period = period;
        this.rentingFee = rentingFee;
        this.status = status;
    }

    // Getters and Setters
    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getRentingFee() {
        return rentingFee;
    }

    public void setRentingFee(double rentingFee) {
        this.rentingFee = rentingFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getId() {
        return agreementId;
    }
}