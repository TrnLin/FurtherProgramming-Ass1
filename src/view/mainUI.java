package view;

import controller.ModifyRentalAgreements;
import controller.RentalManagerImpl;
import java.util.Scanner;

public class mainUI {

    public static void main(String[] args) {
        RentalManagerImpl rentalManager = new RentalManagerImpl();
        rentalManager.loadSampleData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\u001B[34m==========================================================================\u001B[0m");
            System.out.println("\u001B[34mProperty Rental Management System\u001B[0m");
            System.out.println("\u001B[34m==========================================================================\u001B[0m");

            System.out.printf("%-5s %-40s%n", "1", "View all rental agreements");
            System.out.printf("%-5s %-40s%n", "2", "Add a rental agreement");
            System.out.printf("%-5s %-40s%n", "3", "Update a rental agreement");
            System.out.printf("%-5s %-40s%n", "4", "Delete a rental agreement");
            System.out.printf("%-5s %-40s%n", "5", "View rental agreements by owner name");
            System.out.printf("%-5s %-40s%n", "6", "View rental agreements by property address");
            System.out.printf("%-5s %-40s%n", "7", "View rental agreements by status");
            System.out.printf("%-5s %-40s%n", "8", "View all (extended)");
            System.out.printf("%-5s %-40s%n", "9", "Modify (extended)");
            System.out.printf("%-5s %-40s%n", "10", "Exit");
            System.out.println();
            System.out.print("Choose an option: ");

            int choice;
            try {
                if (!scanner.hasNextLine()) {
                    System.out.println("No input found. Exiting...");
                    break;
                }
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    ViewRentalAgreements.viewAllRentalAgreements(rentalManager);
                    break;
                case 2:
                    ModifyRentalAgreements.addRentalAgreement(rentalManager);
                    break;
                case 3:
                    ModifyRentalAgreements.updateRentalAgreement(rentalManager);
                    break;
                case 4:
                    ModifyRentalAgreements.deleteRentalAgreement(rentalManager);
                    break;
                case 5:
                    ViewRentalAgreements.viewRentalAgreementsByOwnerName(rentalManager);
                    break;
                case 6:
                    ViewRentalAgreements.viewRentalAgreementsByPropertyAddress(rentalManager);
                    break;
                case 7:
                    ViewRentalAgreements.viewRentalAgreementsByStatus(rentalManager);
                    break;
                case 8:
                    ViewEntities.viewAll(rentalManager);
                    break;
                case 9:
                    ViewModify.viewModify();
                    break;
                case 10:
                    rentalManager.saveData();
                    System.out.println("Data saved. Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

}