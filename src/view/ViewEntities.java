package view;

import controller.RentalManagerImpl;
import model.Host;
import model.Owner;
import model.Payment;
import model.Tenant;

import java.util.List;

public class ViewEntities {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");

    public  static void viewAll(RentalManagerImpl rentalManager) {

        System.out.println("1. View all payments");
        System.out.println("2. View all tenants");
        System.out.println("3. View all hosts");
        System.out.println("4. View all owners");
        System.out.println("5. View properties");
        System.out.println("6. View all rental agreements");
        System.out.println("7. Exit");
        System.out.println();
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                ViewEntities.viewAllPayments(rentalManager);
                break;
            case 2:
                ViewEntities.viewAllTenants(rentalManager);
                break;
            case 3:
                ViewEntities.viewAllHosts(rentalManager);
                break;
            case 4:
                ViewEntities.viewAllOwners(rentalManager);
                break;
            case 5:
                ViewProperties.viewProperties(rentalManager);
                break;
            case 6:
                ViewRentalAgreements.viewAllRentalAgreements(rentalManager);
                break;
            case 7:
                rentalManager.saveData();
                System.out.println("Data saved. Exiting...");
                scanner.close();
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void viewAllPayments(RentalManagerImpl rentalManager) {
        List<Payment> payments = rentalManager.getAllPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }
        System.out.println("Payments");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s%n",
                "ID", "Agreement ID", "Tenant", "Amount", "Payment Date", "Payment Method", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (Payment payment : payments) {
            String tenant = (payment.getTenant() != null) ? payment.getTenant().getFullName() : "N/A";
            String paymentDate = (payment.getPaymentDate() != null) ? dateFormat.format(payment.getPaymentDate()) : "N/A";

            System.out.printf("%-10s %-20s %-20s %-20.2f %-20s %-15s %-10s%n",
                    payment.getPaymentId(), payment.getAgreementId(), tenant, payment.getAmount(),
                    paymentDate, payment.getPaymentMethod(), payment.getStatus());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        viewAll(rentalManager);
    }

    private static void viewAllTenants(RentalManagerImpl rentalManager) {
        List<Tenant> tenants = rentalManager.getAllTenants();
        if (tenants.isEmpty()) {
            System.out.println("No tenants found.");
            return;
        }
        System.out.println("Tenants");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %n",
                "ID", "Full Name", "Date of Birth", "Contact Information");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (Tenant tenant : tenants) {
            String dateOfBirth = (tenant.getDateOfBirth() != null) ? dateFormat.format(tenant.getDateOfBirth()) : "N/A";
            String contactInformation = (tenant.getContactInformation() != null) ? tenant.getContactInformation() : "N/A";
            System.out.printf("%-10s %-20s %-20s %-20s %n",
                    tenant.getId(), tenant.getFullName(), dateOfBirth, contactInformation);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        viewAll(rentalManager);
    }

    private static void viewAllHosts(RentalManagerImpl rentalManager) {
        List<Host> hosts = rentalManager.getAllHosts();
        if (hosts.isEmpty()) {
            System.out.println("No hosts found.");
            return;
        }
        System.out.println("Hosts");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s n",
                "ID", "Full Name", "Date of Birth", "Contact Information");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (Host host : hosts) {
            String dateOfBirth = (host.getDateOfBirth() != null) ? dateFormat.format(host.getDateOfBirth()) : "N/A";
            String contactInformation = (host.getContactInformation() != null) ? host.getContactInformation() : "N/A";

            System.out.printf("%-10s %-20s %-20s %-20s%n",
                    host.getId(), host.getFullName(), dateOfBirth, contactInformation);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        viewAll(rentalManager);
    }

    public static void viewAllOwners(RentalManagerImpl rentalManager) {
        List<Owner> owners = rentalManager.getAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }
        System.out.println("Owners");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s%n",
                "ID", "Full Name", "Date of Birth", "Contact Information");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (Owner owner : owners) {
            String dateOfBirth = (owner.getDateOfBirth() != null) ? dateFormat.format(owner.getDateOfBirth()) : "N/A";
            String contactInformation = (owner.getContactInformation() != null) ? owner.getContactInformation() : "N/A";
            System.out.printf("%-10s %-20s %-20s %-20s%n",
                    owner.getId(), owner.getFullName(), dateOfBirth, contactInformation);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        viewAll(rentalManager);
    }

}