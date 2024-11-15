package controller;

import model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static util.Prompt.*;

public class ModifyRentalAgreements {
    private static final Scanner scanner = new Scanner(System.in);
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getPeriod() {
        System.out.println();
        System.out.println("1. Monthly");
        System.out.println("2. Weekly");

        String period = scanner.nextLine().trim();
        return switch (period) {
            case "1" -> "Monthly";
            case "2" -> "Weekly";
            default -> {
                System.out.println("Invalid period. Please enter Monthly or Yearly.");
                yield "";
            }
        };
    }

    private static String getStatus() {
        System.out.println();
        System.out.println("1. Active");
        System.out.println("2. New");
        System.out.println("3. Completed");

        String status = scanner.nextLine().trim();
        return switch (status) {
            case "1" -> "Active";
            case "2" -> "New";
            case "3" -> "Completed";
            default -> {
                System.out.println("Invalid status. Please enter Active, New, or Completed.");
                yield "";
            }
        };
    }

    public static void addRentalAgreement(RentalManagerImpl rentalManager) {
        System.out.print("Enter agreement ID: ");
        String agreementId = scanner.nextLine().trim();
    
        // Check if the rental agreement already exists
        boolean agreementExists = rentalManager.getAllRentalAgreements()
                .stream()
                .anyMatch(ra -> ra.getAgreementId().equals(agreementId));
    
        if (agreementExists) {
            System.out.println("Rental agreement already exists. Cannot add a new agreement with the same ID.");
            return;
        }
    
        List<Tenant> tenants = promptForTenants(rentalManager);
        List<Host> hosts = promptForHosts(rentalManager);
        Owner owner = promptForOwner(rentalManager);
        Property property = promptForProperty(rentalManager);
    
        System.out.print("Enter contract date (yyyy-MM-dd): ");
        String contractDateStr = scanner.nextLine().trim();
        System.out.print("Enter rental period (Monthly/Yearly): ");
        String period = getPeriod();



        System.out.print("Enter renting fee: ");
        double rentingFee = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter status (Active/New/Complete): ");
        String status = getStatus();


    
        try {
            RentalAgreement agreement = new RentalAgreement(
                    agreementId, tenants, hosts, owner, property, dateFormat.parse(contractDateStr),
                    period, rentingFee, status
            );
            rentalManager.addRentalAgreement(agreement);
            updateCsv("data/rental_agreements.csv", agreement);
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
        System.out.print("Enter rental period (Monthly/Yearly): ");
        String period = getPeriod();

        System.out.print("Enter renting fee: ");
        double rentingFee = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter status (Active/New/Complete): ");
        String status = getStatus();

        try {
            RentalAgreement updatedAgreement = new RentalAgreement(
                    agreementId, tenants, hosts, owner, property, dateFormat.parse(contractDateStr),
                    period, rentingFee, status
            );
            rentalManager.updateRentalAgreement(agreementId, updatedAgreement);
            updateCsv("data/rental_agreements.csv", updatedAgreement);
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

    private static void updateCsv(String fileName, RentalAgreement agreement) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String tenantNames = agreement.getTenants().stream().map(Tenant::getFullName).collect(Collectors.joining(";"));
            String hostNames = agreement.getHosts().stream().map(Host::getFullName).collect(Collectors.joining(";"));
            writer.append(String.join(",",
                    agreement.getAgreementId(),
                    tenantNames,
                    hostNames,
                    agreement.getOwner().getFullName(),
                    agreement.getProperty().getAddress(),
                    dateFormat.format(agreement.getContractDate()),
                    agreement.getPeriod(),
                    String.valueOf(agreement.getRentingFee()),
                    agreement.getStatus()
            )).append("\n");
        } catch (IOException e) {
            System.err.println("Error updating " + fileName + ": " + e.getMessage());
        }
    }
}