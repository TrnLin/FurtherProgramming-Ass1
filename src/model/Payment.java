/** 
* @author Tran Hoang Linh - S03097 
*/ 
/** 
* @author Tran Hoang Linh - S03097 
*/ 

package model;

import java.util.Date;

/**
 * Represents a payment transaction made by a tenant.
 */
public class Payment {
    private String paymentId;
    private String agreementId;
    private Tenant tenant;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status;

    // Constructor
    public Payment(String paymentId, String agreementId, Tenant tenant, double amount, Date paymentDate, String paymentMethod, String status) {
        this.paymentId = paymentId;
        this.agreementId = agreementId;
        this.tenant = tenant;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", agreementId='" + agreementId + '\'' +
                ", tenant=" + tenant +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}