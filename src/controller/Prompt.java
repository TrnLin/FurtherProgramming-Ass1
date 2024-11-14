package controller;

import model.Host;
import model.Owner;
import model.Property;
import model.RentalAgreement;
import model.Tenant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prompt {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static int tenantIdCounter = 20; // Assuming IDs of current tenants go up to 20
    private static int hostIdCounter = 20; // Assuming IDs of current hosts go up to 20
    private static int ownerIdCounter = 20; // Assuming IDs of current owners go up to 19
    private static int propertyIdCounter = 202; // Assuming IDs of current properties go up to 20
    public static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static List<Tenant> promptForTenants(RentalManagerImpl rentalManager) {
        System.out.println("Enter tenant names (comma-separated): ");
        String[] tenantNames = scanner.nextLine().split(",");
        return Stream.of(tenantNames)
                .map(name -> {
                    String trimmedName = name.trim();
                    Tenant existingTenant = rentalManager.getAllTenants().stream()
                            .filter(tenant -> tenant.getFullName().equalsIgnoreCase(trimmedName))
                            .findFirst()
                            .orElse(null);
                    return existingTenant != null ? existingTenant : createTenant(trimmedName, rentalManager);
                })
                .collect(Collectors.toList());
    }

    private static Tenant createTenant(String name, RentalManagerImpl rentalManager) {
        Tenant existingTenant = rentalManager.getAllTenants().stream()
                .filter(tenant -> tenant.getFullName().equals(name))
                .findFirst()
                .orElse(null);

        if (existingTenant != null) {
            return existingTenant;
        }

        System.out.println("Enter tenant's date of birth (yyyy-MM-dd): ");
        String dobStr = scanner.nextLine();
        Date dob = parseDate(dobStr);

        System.out.println("Enter tenant's contact information: ");
        String contactInformation = scanner.nextLine();

        Tenant tenant = new Tenant("T" + tenantIdCounter++, name, dob, contactInformation);
        rentalManager.addTenant(tenant); // Assume there is a method to add a tenant to RentalManager

        // Update the tenants.csv file
        updateTenantsCsv("data/tenants.csv", tenant);

        return tenant;
    }

    public static List<Host> promptForHosts(RentalManagerImpl rentalManager) {
        System.out.println("Enter host names (comma-separated): ");
        String[] hostNames = scanner.nextLine().split(",");
        return Stream.of(hostNames)
                .map(name -> {
                    String trimmedName = name.trim();
                    Host existingHost = rentalManager.getAllHosts().stream()
                            .filter(host -> host.getFullName().equalsIgnoreCase(trimmedName))
                            .findFirst()
                            .orElse(null);
                    return existingHost != null ? existingHost : createHost(trimmedName, rentalManager);
                })
                .collect(Collectors.toList());
    }

    private static Host createHost(String name, RentalManagerImpl rentalManager) {
        Host existingHost = rentalManager.getAllHosts().stream()
                .filter(host -> host.getFullName().equals(name))
                .findFirst()
                .orElse(null);

        if (existingHost != null) {
            return existingHost;
        }

        System.out.println("Enter host's date of birth (yyyy-MM-dd): ");
        String dobStr = scanner.nextLine();
        Date dob = parseDate(dobStr);

        System.out.println("Enter host's contact information: ");
        String contactInformation = scanner.nextLine();

        Host host = new Host("H" + hostIdCounter++, name, dob, contactInformation);
        rentalManager.addHost(host);

        // Update the hosts.csv file
        updateHostsCsv("data/hosts.csv", host);
        return host;
    }

    public static Owner promptForOwner(RentalManagerImpl rentalManager) {
        System.out.println("Enter owner name: ");
        String ownerName = scanner.nextLine().trim(); // Ensure the name is trimmed

        Owner existingOwner = rentalManager.getAllOwners().stream()
                .filter(owner -> owner.getFullName().equalsIgnoreCase(ownerName)) // Case insensitive comparison
                .findFirst()
                .orElse(null);

        return existingOwner != null ? existingOwner : createOwner(ownerName, rentalManager);
    }

    private static Owner createOwner(String name, RentalManagerImpl rentalManager) {
        System.out.println("Enter owner's date of birth (yyyy-MM-dd): ");
        String dobStr = scanner.nextLine();
        Date dob = parseDate(dobStr);

        System.out.println("Enter owner's contact information: ");
        String contactInformation = scanner.nextLine();

        Owner owner = new Owner("O" + ownerIdCounter++, name, dob, contactInformation);
        rentalManager.addOwner(owner); // Assume there is a method to add an owner to RentalManager

        // Update the owners.csv file
        updateOwnersCsv("data/owners.csv", owner);
        return owner;
    }

    public static Property promptForProperty(RentalManagerImpl rentalManager) {
        System.out.println("Enter property address: ");
        String address = scanner.nextLine().trim(); // Ensure the address is trimmed

        Property existingProperty = rentalManager.getAllProperties().stream()
                .filter(property -> property.getAddress().equalsIgnoreCase(address)) // Case insensitive comparison
                .findFirst()
                .orElse(null);

        if (existingProperty != null) {
            return existingProperty;
        }

        // Update the properties.csv file
        updateCsv("data/properties.csv", address);

        return createProperty(address, rentalManager);
    }

    private static Property createProperty(String address, RentalManagerImpl rentalManager) {
        Property property = new Property("P" + propertyIdCounter++, address, 0.0, "");
        rentalManager.addProperty(property); // Assume there is a method to add a property to RentalManager
        updatePropertyCsv("data/properties.csv", property);
        return property;
    }

    public static void updateCsv(String fileName, Object entity) {
        try (FileWriter csvWriter = new FileWriter(fileName, true)) {
            if (entity instanceof RentalAgreement) {
                RentalAgreement agreement = (RentalAgreement) entity;
                StringBuilder sb = new StringBuilder();
                sb.append(agreement.getAgreementId()).append(",");
                sb.append(agreement.getTenants().stream().map(Tenant::getFullName).collect(Collectors.joining(";"))).append(",");
                sb.append(agreement.getHosts().stream().map(Host::getFullName).collect(Collectors.joining(";"))).append(",");
                sb.append(agreement.getOwner().getFullName()).append(",");
                sb.append(agreement.getProperty().getAddress()).append(",");
                sb.append(dateFormat.format(agreement.getContractDate())).append(",");
                sb.append(agreement.getPeriod()).append(",");
                sb.append(agreement.getRentingFee()).append(",");
                sb.append(agreement.getStatus()).append("\n");
                csvWriter.append(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTenantsCsv(String fileName, Tenant tenant) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Format tenant details as CSV row
            String dob = dateFormat.format(tenant.getDateOfBirth());
            String csvRow = String.format("%s,%s,%s,%s\n",
                    tenant.getId(),
                    tenant.getFullName(),
                    dob,
                    tenant.getContactInformation());

            // Write tenant details to CSV file
            writer.write(csvRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void updateOwnersCsv(String fileName, Owner owner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Format owner details as CSV row
            String dob = dateFormat.format(owner.getDateOfBirth());
            String csvRow = String.format("%s,%s,%s,%s\n",
                    owner.getId(),
                    owner.getFullName(),
                    dob,
                    owner.getContactInformation());

            // Write owner details to CSV file
            writer.write(csvRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateHostsCsv(String fileName, Host host) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Format host details as CSV row
            String dob = dateFormat.format(host.getDateOfBirth());
            String csvRow = String.format("%s,%s,%s,%s\n",
                    host.getId(),
                    host.getFullName(),
                    dob,
                    host.getContactInformation());

            // Write host details to CSV file
            writer.write(csvRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updatePropertyCsv(String fileName, Property property) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Format property details as CSV row
            String csvRow = String.format("%s,%s,%.2f,%s\n",
                    property.getPropertyId(),
                    property.getAddress(),
                    property.getPricing(),
                    property.getStatus());

            // Write property details to CSV file
            writer.write(csvRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            System.err.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return null;
        }
    }
}