
import java.text.SimpleDateFormat;
import java.util.Scanner;

import view.*;

import controller.ModifyRentalAgreements;
import controller.RentalManagerImpl;

public class Main {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        RentalManagerImpl rentalManager = new RentalManagerImpl();
        rentalManager.loadSampleData();


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
            System.out.printf("%-5s %-40s%n", "9", "Exit");
            System.out.println();
            System.out.print("Choose an option: ");

            int choice;
            try {
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