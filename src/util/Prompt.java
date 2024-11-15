package util;

import controller.RentalManagerImpl;
import model.Host;
import model.Owner;
import model.Property;
import model.Tenant;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static util.saveFile.*;

public class Prompt {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static int tenantIdCounter = 20; // Assuming IDs of current tenants go up to 20
    private static int hostIdCounter = 20; // Assuming IDs of current hosts go up to 20
    private static int ownerIdCounter = 20; // Assuming IDs of current owners go up to 19
    private static int propertyIdCounter = 202; // Assuming IDs of current properties go up to 20
    public static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
     // Assuming this list will hold all tenants'
    public static List<Tenant> tenants = new ArrayList<>();
    public static List<Host> hosts = new ArrayList<>();
    public static List<Owner> owners = new ArrayList<>();
    public static List<Property> properties = new ArrayList<>();

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
        updateTenantsCsv("data/tenants.csv", tenants);

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
        updateHostsCsv("data/hosts.csv", hosts);
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
        updateOwnersCsv("data/owners.csv", owners);
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
        updatePropertyCsv("data/properties.csv", properties);
        return property;
    }



    private static Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + dateStr);
            return null;
        }
    }
}