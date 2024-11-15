/** 
* @author Tran Hoang Linh - S03097 
*/ 

package view;
import controller.RentalManagerImpl;
import model.Host;
import model.RentalAgreement;
import model.Tenant;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ViewRentalAgreements {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final Scanner scanner = new Scanner(System.in);

    public static void viewAllRentalAgreements(RentalManagerImpl rentalManager) {
        if (rentalManager == null) {
            throw new IllegalArgumentException("RentalManagerImpl cannot be null");
        }

        System.out.println();
        System.out.println("Rental Agreements");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10s %-10s%n",
                "ID", "Tenants", "Hosts", "Owner", "Property", "Contract Date", "Period", "Fee", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

        List<RentalAgreement> rentalAgreements = rentalManager.getAllRentalAgreements();
        if (rentalAgreements.isEmpty()) {
            System.out.println("No rental agreements found.");
            return;
        }

        for (RentalAgreement agreement : rentalAgreements) {
            String tenants = (agreement.getTenants() != null && agreement.getTenants().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getTenants().stream().map(Tenant::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String hosts = (agreement.getHosts() != null && agreement.getHosts().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getHosts().stream().map(Host::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String owner = (agreement.getOwner() != null) ? agreement.getOwner().getFullName() : "N/A";
            String contractDate = (agreement.getContractDate() != null) ? dateFormat.format(agreement.getContractDate()) : "N/A";
            String property = (agreement.getProperty() != null) ? agreement.getProperty().getAddress() : "N/A";

            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10.2f %-10s%n",
                    agreement.getAgreementId(), tenants, hosts, owner, property, contractDate,
                    agreement.getPeriod(), agreement.getRentingFee(), agreement.getStatus());
        }

        System.out.println();
        System.out.println();
        System.out.println("Choose sorting option: ");
        System.out.println("1. Sort by Agreement ID");
        System.out.println("2. Sort by Contract Date");
        System.out.println("3. Sort by Renting Fee");
        System.out.println("4. Sort by Status");
        System.out.println("5. Exit");
        System.out.println();
        System.out.print("Choose a sorting option: ");
        int sortOption = Integer.parseInt(scanner.nextLine());

        Comparator<RentalAgreement> comparator = switch (sortOption) {
            case 1 -> Comparator.comparing(RentalAgreement::getAgreementId);
            case 2 -> Comparator.comparing(RentalAgreement::getContractDate);
            case 3 -> Comparator.comparing(RentalAgreement::getRentingFee);
            case 4 -> Comparator.comparing(RentalAgreement::getStatus);
            case 5 -> {
                System.out.println("Exiting...");
                scanner.close();
                yield null;

            }
            default -> {
                System.out.println("Invalid option. Displaying unsorted list.");
                yield Comparator.comparing(RentalAgreement::getAgreementId);
            }
        };

        List<RentalAgreement> sortedRentalAgreements = rentalManager.getSortedRentalAgreements(comparator);
        if (rentalAgreements.isEmpty()) {
            System.out.println("No rental agreements found.");
            return;
        }
        System.out.println("Rental Agreements");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10s %-10s%n",
                "ID", "Tenants", "Hosts", "Owner", "Property", "Contract Date", "Period", "Fee", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (RentalAgreement agreement : sortedRentalAgreements) {
            String tenants = (agreement.getTenants() != null && agreement.getTenants().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getTenants().stream().map(Tenant::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String hosts = (agreement.getHosts() != null && agreement.getHosts().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getHosts().stream().map(Host::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String owner = (agreement.getOwner() != null) ? agreement.getOwner().getFullName() : "N/A";
            String contractDate = (agreement.getContractDate() != null) ? dateFormat.format(agreement.getContractDate()) : "N/A";
            String property = (agreement.getProperty() != null) ? agreement.getProperty().getAddress() : "N/A";

            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10.2f %-10s%n",
                    agreement.getAgreementId(), tenants, hosts, owner, property, contractDate,
                    agreement.getPeriod(), agreement.getRentingFee(), agreement.getStatus());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void viewRentalAgreementsByOwnerName(RentalManagerImpl rentalManager) {
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();
        List<RentalAgreement> rentalAgreements = rentalManager.getRentalAgreementsByOwnerName(ownerName);
        if (rentalAgreements.isEmpty()) {
            System.out.println("No rental agreements found for the given owner name.");
            return;
        }
        System.out.println("Rental Agreements for Owner: " + ownerName);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10s %-10s%n",
                "ID", "Tenants", "Hosts", "Owner", "Property", "Contract Date", "Period", "Fee", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (RentalAgreement agreement : rentalAgreements) {
            String tenants = (agreement.getTenants() != null && agreement.getTenants().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getTenants().stream().map(Tenant::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String hosts = (agreement.getHosts() != null && agreement.getHosts().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getHosts().stream().map(Host::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String owner = (agreement.getOwner() != null) ? agreement.getOwner().getFullName() : "N/A";
            String contractDate = (agreement.getContractDate() != null) ? dateFormat.format(agreement.getContractDate()) : "N/A";
            String property = (agreement.getProperty() != null) ? agreement.getProperty().getAddress() : "N/A";

            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10.2f %-10s%n",
                    agreement.getAgreementId(), tenants, hosts, owner, property, contractDate,
                    agreement.getPeriod(), agreement.getRentingFee(), agreement.getStatus());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void viewRentalAgreementsByPropertyAddress(RentalManagerImpl rentalManager) {
        System.out.print("Enter property address: ");
        String propertyAddress = scanner.nextLine();
        List<RentalAgreement> rentalAgreements = rentalManager.getRentalAgreementsByPropertyAddress(propertyAddress);
        if (rentalAgreements.isEmpty()) {
            System.out.println("No rental agreements found for the given property address.");
            return;
        }
        System.out.println("Rental Agreements for Property Address: " + propertyAddress);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10s %-10s%n",
                "ID", "Tenants", "Hosts", "Owner", "Property", "Contract Date", "Period", "Fee", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (RentalAgreement agreement : rentalAgreements) {
            String tenants = (agreement.getTenants() != null && agreement.getTenants().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getTenants().stream().map(Tenant::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String hosts = (agreement.getHosts() != null && agreement.getHosts().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getHosts().stream().map(Host::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String owner = (agreement.getOwner() != null) ? agreement.getOwner().getFullName() : "N/A";
            String contractDate = (agreement.getContractDate() != null) ? dateFormat.format(agreement.getContractDate()) : "N/A";
            String property = (agreement.getProperty() != null) ? agreement.getProperty().getAddress() : "N/A";

            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10.2f %-10s%n",
                    agreement.getAgreementId(), tenants, hosts, owner, property, contractDate,
                    agreement.getPeriod(), agreement.getRentingFee(), agreement.getStatus());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void viewRentalAgreementsByStatus(RentalManagerImpl rentalManager) {
        System.out.print("Enter status: ");
        String status = scanner.nextLine();
        List<RentalAgreement> rentalAgreements = rentalManager.getRentalAgreementsByStatus(status);
        if (rentalAgreements.isEmpty()) {
            System.out.println("No rental agreements found for the given status.");
            return;
        }
        System.out.println("Rental Agreements with Status: " + status);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10s %-10s%n",
                "ID", "Tenants", "Hosts", "Owner", "Property", "Contract Date", "Period", "Fee", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        for (RentalAgreement agreement : rentalAgreements) {
            String tenants = (agreement.getTenants() != null && agreement.getTenants().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getTenants().stream().map(Tenant::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String hosts = (agreement.getHosts() != null && agreement.getHosts().stream().allMatch(java.util.Objects::nonNull))
                    ? agreement.getHosts().stream().map(Host::getFullName).collect(Collectors.joining(";"))
                    : "N/A";
            String owner = (agreement.getOwner() != null) ? agreement.getOwner().getFullName() : "N/A";
            String contractDate = (agreement.getContractDate() != null) ? dateFormat.format(agreement.getContractDate()) : "N/A";
            String property = (agreement.getProperty() != null) ? agreement.getProperty().getAddress() : "N/A";

            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-15s %-10s %-10.2f %-10s%n",
                    agreement.getAgreementId(), tenants, hosts, owner, property, contractDate,
                    agreement.getPeriod(), agreement.getRentingFee(), agreement.getStatus());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    }

}
