package controller;

import model.Tenant;
import view.mainUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import  util.saveFile;

public class ModifyTenants {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static List<Tenant> tenants = new ArrayList<>(); // Assuming this list will hold all tenants

    public static void ViewModifyTenants() {
        while (true) {
            System.out.println("Modify Tenants");
            System.out.println();
            System.out.println("1. Add a new tenant");
            System.out.println("2. Update an existing tenant");
            System.out.println("3. Delete a tenant");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Choose an option: ");

            int choice;
            try {
                if (!scanner.hasNextLine()) {
                    System.out.println("No input found. Exiting...");
                    return;
                }
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addTenant();
                    saveFile.updateTenantsCsv("data/tenants.csv", tenants);
                    break;
                case 2:
                    updateTenant();
                    saveFile.updateTenantsCsv("data/tenants.csv", tenants);
                    break;
                case 3:
                    deleteTenant();
                    saveFile.updateTenantsCsv("data/tenants.csv", tenants);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    String[] args = new String[0];
                    mainUI.main(args);
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void addTenant() {
        System.out.print("Enter tenant ID: ");
        if (!scanner.hasNextLine()) {
            System.out.println("No input found. Exiting...");
            return;
        }
        String tenantId = scanner.nextLine();

        System.out.print("Enter tenant name: ");
        if (!scanner.hasNextLine()) {
            System.out.println("No input found. Exiting...");
            return;
        }
        String tenantName = scanner.nextLine();

        System.out.print("Enter date of birth (MM/dd/yyyy): ");
        Date dateOfBirth = null;
        try {
            if (!scanner.hasNextLine()) {
                System.out.println("No input found. Exiting...");
                return;
            }
            dateOfBirth = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        System.out.print("Enter contact information: ");
        if (!scanner.hasNextLine()) {
            System.out.println("No input found. Exiting...");
            return;
        }
        String contactInfo = scanner.nextLine();

        Tenant newTenant = new Tenant(tenantId, tenantName, dateOfBirth, contactInfo);
        tenants.add(newTenant);

        System.out.println("Tenant added successfully!");
    }

    public static void updateTenant() {
        System.out.print("Enter tenant ID to update: ");
        if (!scanner.hasNextLine()) {
            System.out.println("No input found. Exiting...");
            return;
        }
        String tenantId = scanner.nextLine();

        Tenant tenantToUpdate = null;
        for (Tenant tenant : tenants) {
            if (tenant.getId().equals(tenantId)) {
                tenantToUpdate = tenant;
                break;
            }
        }

        if (tenantToUpdate == null) {
            System.out.println("Tenant not found.");
            return;
        }

        System.out.println("Updating a tenant");
        System.out.println();
        System.out.println("1. Update tenant name");
        System.out.println("2. Update tenant contact information");
        System.out.println("3. Exit");
        System.out.println();
        System.out.print("Choose an option: ");

        int choice;
        try {
            if (!scanner.hasNextLine()) {
                System.out.println("No input found. Exiting...");
                return;
            }
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                System.out.print("Enter new tenant name: ");
                if (!scanner.hasNextLine()) {
                    System.out.println("No input found. Exiting...");
                    return;
                }
                String newTenantName = scanner.nextLine();
                tenantToUpdate.setFullName(newTenantName);
                System.out.println("Tenant name updated successfully!");
                break;
            case 2:
                System.out.print("Enter new contact information: ");
                if (!scanner.hasNextLine()) {
                    System.out.println("No input found. Exiting...");
                    return;
                }
                String newContactInfo = scanner.nextLine();
                tenantToUpdate.setContactInformation(newContactInfo);
                System.out.println("Contact information updated successfully!");
                break;
            case 3:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void deleteTenant() {
        System.out.print("Enter tenant ID to delete: ");
        if (!scanner.hasNextLine()) {
            System.out.println("No input found. Exiting...");
            return;
        }
        String tenantId = scanner.nextLine();

        Tenant tenantToDelete = null;
        for (Tenant tenant : tenants) {
            if (tenant.getId().equals(tenantId)) {
                tenantToDelete = tenant;
                break;
            }
        }

        if (tenantToDelete == null) {
            System.out.println("Tenant not found.");
            return;
        }

        tenants.remove(tenantToDelete);
        System.out.println("Tenant deleted successfully!");
    }
}