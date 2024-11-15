package controller;

import model.Owner;
import view.mainUI;
import util.saveFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ModifyOwners {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static List<Owner> owners = new ArrayList<>(); // Assuming this list will hold all owners

    public static void ViewModifyOwners() {
        while (true) {
            System.out.println("Modify Owners");
            System.out.println();
            System.out.println("1. Add a new owner");
            System.out.println("2. Update an existing owner");
            System.out.println("3. Delete an owner");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addOwner();
                    saveFile.updateOwnersCsv("data/owners.csv", owners);
                    break;
                case 2:
                    updateOwner();
                    saveFile.updateOwnersCsv("data/owners.csv", owners);
                    break;
                case 3:
                    deleteOwner();
                    saveFile.updateOwnersCsv("data/owners.csv", owners);
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

    private static void addOwner() {
        System.out.print("Enter owner ID: ");
        String ownerId = scanner.nextLine();
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();
        System.out.print("Enter date of birth (MM/dd/yyyy): ");
        Date dateOfBirth = null;
        try {
            dateOfBirth = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }
        System.out.print("Enter contact information: ");
        String contactInfo = scanner.nextLine();

        Owner newOwner = new Owner(ownerId, ownerName, dateOfBirth, contactInfo);
        owners.add(newOwner);

        System.out.println("Owner added successfully!");
    }

    public static void updateOwner() {
        System.out.print("Enter owner ID to update: ");
        String ownerId = scanner.nextLine();

        Owner ownerToUpdate = null;
        for (Owner owner : owners) {
            if (owner.getId().equals(ownerId)) {
                ownerToUpdate = owner;
                break;
            }
        }

        if (ownerToUpdate == null) {
            System.out.println("Owner not found.");
            return;
        }

        System.out.println("Updating an owner");
        System.out.println();
        System.out.println("1. Update owner name");
        System.out.println("2. Update owner contact information");
        System.out.println("3. Exit");
        System.out.println();
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Enter new owner name: ");
                String newOwnerName = scanner.nextLine();
                ownerToUpdate.setFullName(newOwnerName);
                System.out.println("Owner name updated successfully!");
                break;
            case 2:
                System.out.print("Enter new contact information: ");
                String newContactInfo = scanner.nextLine();
                ownerToUpdate.setContactInformation(newContactInfo);
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

    private static void deleteOwner() {
        System.out.print("Enter owner ID to delete: ");
        String ownerId = scanner.nextLine();

        Owner ownerToDelete = null;
        for (Owner owner : owners) {
            if (owner.getId().equals(ownerId)) {
                ownerToDelete = owner;
                break;
            }
        }

        if (ownerToDelete == null) {
            System.out.println("Owner not found.");
            return;
        }

        owners.remove(ownerToDelete);
        System.out.println("Owner deleted successfully!");
    }
}