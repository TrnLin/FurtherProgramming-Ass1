/** 
* @author Tran Hoang Linh - S03097 
*/ 
package controller;

import model.Host;
import view.mainUI;

import util.saveFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import static util.saveFile.updateHostsCsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ModifyHost {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static int hostIdCounter = 20; // Assuming IDs of current hosts go up to 20
    private static List<Host> hosts = new ArrayList<>(); // Assuming this list will hold all hosts

    // This method is called from mainUI.java
    // It displays the options for modifying hosts
    public static void ViewModifyHost() {
        System.out.println("Modifying a host");
        System.out.println();
        System.out.println("1. Add a new host");
        System.out.println("2. Update an existing host");
        System.out.println("3. Delete a host");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());


        // This switch statement handles the user's choice
        switch (choice) {
            case 1:
                addHost();
                saveFile.updateHostsCsv("data/hosts.csv", hosts);
                break;
            case 2:
                updateHost();
                saveFile.updateHostsCsv("data/hosts.csv", hosts);
                break;
            case 3:
                deleteHost();
                saveFile.updateHostsCsv("data/hosts.csv", hosts);
                break;
            case 4:
                System.out.println("Exiting...");
                scanner.close();
                String[] args;
                args = new String[0];
                mainUI.main(args);
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void addHost() {
        System.out.print("Enter host name: ");
        String hostName = scanner.nextLine();
        System.out.print("Enter contact information: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter date of birth (yyyy-MM-dd): ");
        String dateOfBirthStr = scanner.nextLine();
        Date dateOfBirth = null;

        // This try-catch block is used to parse the date of birth
        try {
            dateOfBirth = dateFormat.parse(dateOfBirthStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Host host = new Host("H" + hostIdCounter++, hostName, dateFormat.format(dateOfBirth), contactInfo);

        hosts.add(host);
        updateHostsCsv("data/hosts.csv", hosts);
        System.out.println("Host added successfully!");
    }

    public static void updateHost() {
        System.out.println("Updating a host");
        System.out.println();
        System.out.println("1. Update host name");
        System.out.println("2. Update host contact information");
        System.out.println("3. Exit");
        System.out.println();
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Enter host ID: ");
                String hostId = scanner.nextLine();
                System.out.print("Enter new host name: ");
                String hostName = scanner.nextLine();
                updateHostDetail(hostId, hostName, null);
                break;
            case 2:
                System.out.print("Enter host ID: ");
                String hostId2 = scanner.nextLine();
                System.out.print("Enter new contact information: ");
                String contactInfo = scanner.nextLine();
                updateHostDetail(hostId2, null, contactInfo);
                break;
            case 3:
                System.out.println("Exiting...");
                scanner.close();
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void deleteHost() {
        System.out.print("Enter host ID to delete: ");
        String hostId = scanner.nextLine();

        Host hostToDelete = null;
        for (Host host : hosts) {
            if (host.getId().equals(hostId)) {
                hostToDelete = host;
                break;
            }
        }

        if (hostToDelete == null) {
            System.out.println("Host not found.");
            return;
        }

        hosts.remove(hostToDelete);
        System.out.println("Host deleted successfully!");
        updateHostsCsv("data/hosts.csv", hosts);
    }

    private static void updateHostDetail(String hostId, String newName, String newContact) {
        // This try-with-resources block reads the hosts.csv file
        try (BufferedReader br = new BufferedReader(new FileReader("data/hosts.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(hostId)) {
                    // This block updates the host's name and contact information
                    Host host = new Host(values[0], values[1], dateFormat.parse(values[2]), values[3]);
                    if (newName != null) {
                        host.setFullName(newName);
                    }
                    if (newContact != null) {
                        host.setContactInformation(newContact);
                    }
                    updateHostsCsv("data/hosts.csv", hosts);
                    System.out.println("Host updated successfully!");
                    return;
                }
            }
            System.out.println("Host ID not found.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
