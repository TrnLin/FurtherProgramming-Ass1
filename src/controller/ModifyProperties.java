/** 
* @author Tran Hoang Linh - S03097 
*/ 

package controller;

import model.Property;
import view.mainUI;
import util.saveFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModifyProperties {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Property> properties = new ArrayList<>(); // Assuming this list will hold all properties

    public static void ViewModifyProperties() {
        while (true) {
            System.out.println("Modify Properties");
            System.out.println();
            System.out.println("1. Add a new property");
            System.out.println("2. Update an existing property");
            System.out.println("3. Delete a property");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addProperty();
                    saveFile.updatePropertyCsv("data/properties.csv", properties);
                    break;
                case 2:
                    updateProperty();
                    saveFile.updatePropertyCsv("data/properties.csv", properties);
                    break;
                case 3:
                    deleteProperty();
                    saveFile.updatePropertyCsv("data/properties.csv", properties);
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

    private static void addProperty() {
        System.out.print("Enter property ID: ");
        String propertyId = scanner.nextLine();
        System.out.print("Enter property address: ");
        String address = scanner.nextLine();
        System.out.print("Enter property pricing: ");
        double pricing = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter property status: ");
        String status = scanner.nextLine();

        Property newProperty = new Property(propertyId, address, pricing, status);
        properties.add(newProperty);

        System.out.println("Property added successfully!");
    }

    public static void updateProperty() {
        System.out.print("Enter property ID to update: ");
        String propertyId = scanner.nextLine();

        Property propertyToUpdate = null;
        for (Property property : properties) {
            if (property.getPropertyId().equals(propertyId)) {
                propertyToUpdate = property;
                break;
            }
        }

        if (propertyToUpdate == null) {
            System.out.println("Property not found.");
            return;
        }

        System.out.println("Updating a property");
        System.out.println();
        System.out.println("1. Update property address");
        System.out.println("2. Update property pricing");
        System.out.println("3. Update property status");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Enter new property address: ");
                String newAddress = scanner.nextLine();
                propertyToUpdate.setAddress(newAddress);
                System.out.println("Property address updated successfully!");
                break;
            case 2:
                System.out.print("Enter new property pricing: ");
                double newPricing = Double.parseDouble(scanner.nextLine());
                propertyToUpdate.setPricing(newPricing);
                System.out.println("Property pricing updated successfully!");
                break;
            case 3:
                System.out.print("Enter new property status: ");
                String newStatus = scanner.nextLine();
                propertyToUpdate.setStatus(newStatus);
                System.out.println("Property status updated successfully!");
                break;
            case 4:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void deleteProperty() {
        System.out.print("Enter property ID to delete: ");
        String propertyId = scanner.nextLine();

        Property propertyToDelete = null;
        for (Property property : properties) {
            if (property.getPropertyId().equals(propertyId)) {
                propertyToDelete = property;
                break;
            }
        }

        if (propertyToDelete == null) {
            System.out.println("Property not found.");
            return;
        }

        properties.remove(propertyToDelete);
        System.out.println("Property deleted successfully!");
    }
}