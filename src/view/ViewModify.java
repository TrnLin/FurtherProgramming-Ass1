/** 
* @author Tran Hoang Linh - S03097 
*/ 

package view;

import controller.*;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ViewModify {
    private static final Scanner scanner = new Scanner(System.in);
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");


    public static void viewModify() {
        System.out.println("1. Modify a tenant");
        System.out.println("2. Modify a host");
        System.out.println("3. Modify an owner");
        System.out.println("4. Modify a property");
        System.out.println("5. Exit");
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
                ModifyTenants.ViewModifyTenants();
                break;
            case 2:
                ModifyHost.ViewModifyHost();
                break;
            case 3:
                ModifyOwners.ViewModifyOwners();
                break;
            case 4:
                ModifyProperties.ViewModifyProperties();
                break;
            case 5:
                System.out.println("Exiting...");
                scanner.close();
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}