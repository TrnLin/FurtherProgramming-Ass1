package controller;

import model.*;
import java.util.List;

public interface RentalManager {
    void addRentalAgreement(RentalAgreement agreement);
    void updateRentalAgreement(String agreementId, RentalAgreement updatedAgreement);
    void deleteRentalAgreement(String agreementId);
    List<RentalAgreement> getAllRentalAgreements();
    List<RentalAgreement> getRentalAgreementsByOwnerName(String ownerName);
    List<RentalAgreement> getRentalAgreementsByPropertyAddress(String propertyAddress);
    List<RentalAgreement> getRentalAgreementsByStatus(String status);
    List<Tenant> getAllTenants();
    List<Host> getAllHosts();
    List<Owner> getAllOwners();
    List<ResidentialProperty> getAllResidentialProperties();
    List<CommercialProperty> getAllCommercialProperties();
    List<Payment> getAllPayments();
    List<Property> getAllProperties();

}