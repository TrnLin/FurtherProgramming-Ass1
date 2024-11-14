package view;

import controller.RentalManagerImpl;
import model.CommercialProperty;
import model.ResidentialProperty;

import java.util.List;
import java.util.Scanner;

public class ViewProperties {
    private static final Scanner scanner = new Scanner(System.in);


    public static void viewProperties(RentalManagerImpl rentalManager) {

        System.out.println("1. View all residential properties");
        System.out.println("2. View all commercial properties");
        System.out.println("3. View all properties");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print("Choose an option: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                viewAllResidentialProperties(rentalManager);
                break;
            case 2:
                viewAllCommercialProperties(rentalManager);
                break;
            case 3:
                viewAllProperties(rentalManager);
                break;
            case 4:
                rentalManager.saveData();
                System.out.println("Data saved. Exiting...");
                scanner.close();
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void viewAllProperties(RentalManagerImpl rentalManager) {
        List<ResidentialProperty> residentialProperties = rentalManager.getAllResidentialProperties();
        List<CommercialProperty> commercialProperties = rentalManager.getAllCommercialProperties();
        if (residentialProperties.isEmpty() && commercialProperties.isEmpty()) {
            System.out.println("No properties found.");
            return;
        }
        System.out.println("Properties");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %n",
                "ID", "Address", "Type", "Rent", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (ResidentialProperty property : residentialProperties) {
            String rent = String.format("%.2f", property.getPricing());
            String status = property.getStatus();

            System.out.printf("%-10s %-20s %-20s %-20s %-20s %n",
                    property.getPropertyId(), property.getAddress(), "Residential", rent, status);
        }
        for (CommercialProperty property : commercialProperties) {
            String rent = String.format("%.2f", property.getPricing());
            String status = property.getStatus();

            System.out.printf("%-10s %-20s %-20s %-20s %-20s %n",
                    property.getPropertyId(), property.getAddress(), "Commercial", rent, status);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

        viewProperties(rentalManager);
    }

    private static void viewAllResidentialProperties(RentalManagerImpl rentalManager) {
        List<ResidentialProperty> residentialProperties = rentalManager.getAllResidentialProperties();
        if (residentialProperties.isEmpty()) {
            System.out.println("No residential properties found.");
            return;
        }
        System.out.println("Residential Properties");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %n",
                "ID", "Address", "Type", "Rent", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (ResidentialProperty property : residentialProperties) {
            String rent = String.format("%.2f", property.getPricing());
            String status = property.getStatus();
            System.out.printf("%-10s %-20s %-20s %-20s %-20s %n",
                    property.getPropertyId(), property.getAddress(), "Residential", rent, status);
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        viewProperties(rentalManager);

    }

    private static void viewAllCommercialProperties(RentalManagerImpl rentalManager) {
        List<CommercialProperty> commercialProperties = rentalManager.getAllCommercialProperties();
        if (commercialProperties.isEmpty()) {
            System.out.println("No commercial properties found.");
            return;
        }
        System.out.println("Commercial Properties");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %n",
                "ID", "Address", "Type", "Rent", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (CommercialProperty property : commercialProperties) {
            String rent = String.format("%.2f", property.getPricing());
            String status = property.getStatus();
            System.out.printf("%-10s %-20s %-20s %-20s %-20s %n",
                    property.getPropertyId(), property.getAddress(), "Commercial", rent, status);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        viewProperties(rentalManager);
    }

}