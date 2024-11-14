package controller;

import controller.RentalManagerImpl;
import model.*;

import java.text.ParseException;
import java.util.List;
import controller.Prompt.*;

import static controller.Prompt.*;

public class ModifyRentalAgreements {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    public static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

    public static void addRentalAgreement(RentalManagerImpl rentalManager) {
        System.out.print("Enter agreement ID: ");
        String agreementId = scanner.nextLine();

        List<Tenant> tenants = promptForTenants(rentalManager);
        List<Host> hosts = promptForHosts(rentalManager);
        Owner owner = promptForOwner(rentalManager);
        Property property = promptForProperty(rentalManager);

        System.out.print("Enter contract date (yyyy-MM-dd): ");
        String contractDateStr = scanner.nextLine();
        System.out.print("Enter rental period: ");
        String period = scanner.nextLine();
        System.out.print("Enter renting fee: ");
        double rentingFee = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter status: ");
        String status = scanner.nextLine();

        try {
            RentalAgreement agreement = new RentalAgreement(
                    agreementId, tenants, hosts, owner, property, dateFormat.parse(contractDateStr),
                    period, rentingFee, status
            );
            rentalManager.addRentalAgreement(agreement);
            System.out.println("Rental agreement added successfully.");
        } catch (ParseException e) {
            System.err.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        }
    }

    public static void updateRentalAgreement(RentalManagerImpl rentalManager) {
        System.out.print("Enter agreement ID to update: ");
        String agreementId = scanner.nextLine();

        RentalAgreement oldAgreement = rentalManager.getAllRentalAgreements()
                .stream()
                .filter(ra -> ra.getAgreementId().equals(agreementId))
                .findFirst()
                .orElse(null);

        if (oldAgreement == null) {
            System.out.println("Rental agreement not found.");
            return;
        }

        List<Tenant> tenants = promptForTenants(rentalManager);
        List<Host> hosts = promptForHosts(rentalManager);
        Owner owner = promptForOwner(rentalManager);
        Property property = promptForProperty(rentalManager);

        System.out.print("Enter contract date (yyyy-MM-dd): ");
        String contractDateStr = scanner.nextLine();
        System.out.print("Enter rental period: ");
        String period = scanner.nextLine();
        System.out.print("Enter renting fee: ");
        double rentingFee = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter status: ");
        String status = scanner.nextLine();

        try {
            RentalAgreement updatedAgreement = new RentalAgreement(
                    agreementId, tenants, hosts, owner, property, dateFormat.parse(contractDateStr),
                    period, rentingFee, status
            );
            rentalManager.updateRentalAgreement(agreementId, updatedAgreement);
            System.out.println("Rental agreement updated successfully.");
        } catch (ParseException e) {
            System.err.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        }
    }

    public static void deleteRentalAgreement(RentalManagerImpl rentalManager) {
        System.out.print("Enter agreement ID to delete: ");
        String agreementId = scanner.nextLine();
        rentalManager.deleteRentalAgreement(agreementId);
        System.out.println("Rental agreement deleted successfully.");
    }
}