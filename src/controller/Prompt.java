package controller;

import model.Host;
import model.Owner;
import model.Property;
import model.Tenant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prompt {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static int tenantIdCounter = 21; // Assuming IDs of current tenants go up to 20
    private static int hostIdCounter = 21; // Assuming IDs of current hosts go up to 20
    private static int ownerIdCounter = 20; // Assuming IDs of current owners go up to 19
    private static int propertyIdCounter = 21; // Assuming IDs of current properties go up to 20

    public static List<Tenant> promptForTenants(RentalManagerImpl rentalManager) {
        System.out.print("Enter tenant names (semicolon separated): ");
        String tenantNames = scanner.nextLine();
        return Stream.of(tenantNames.split(";"))
                .map(name -> rentalManager.getAllTenants().stream()
                        .filter(t -> t.getFullName().equals(name))
                        .findFirst()
                        .orElseGet(() -> createTenant(name, rentalManager)))
                .collect(Collectors.toList());
    }

    private static Tenant createTenant(String name, RentalManagerImpl rentalManager) {
        String email = name.toLowerCase().replace(" ", "") + "@example.com"; // Simplified email generation
        Tenant newTenant = new Tenant(String.valueOf(tenantIdCounter++), name, "1990-01-01", email); // Modify the constructor if necessary
        rentalManager.addTenant(newTenant);
        updateCsv("data/tenants.csv", newTenant);

        System.out.println("Created new tenant: " + name);
        return newTenant;
    }

    public static List<Host> promptForHosts(RentalManagerImpl rentalManager) {
        System.out.print("Enter host names (semicolon separated): ");
        String hostNames = scanner.nextLine();
        return Stream.of(hostNames.split(";"))
                .map(name -> rentalManager.getAllHosts().stream()
                        .filter(h -> h.getFullName().equals(name))
                        .findFirst()
                        .orElseGet(() -> createHost(name, rentalManager)))
                .collect(Collectors.toList());
    }

    private static Host createHost(String name, RentalManagerImpl rentalManager) {
        String email = name.toLowerCase().replace(" ", "") + "@example.com"; // Simplified email generation
        Host newHost = new Host(String.valueOf(hostIdCounter++), name, "1980-01-01", email); // Modify the constructor if necessary
        rentalManager.addHost(newHost);
        updateCsv("data/hosts.csv", newHost);
        System.out.println("Created new host: " + name);
        return newHost;
    }

    public static Owner promptForOwner(RentalManagerImpl rentalManager) {
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();
        return rentalManager.getAllOwners().stream()
                .filter(o -> o.getFullName().equals(ownerName))
                .findFirst()
                .orElseGet(() -> createOwner(ownerName, rentalManager));
    }

    private static Owner createOwner(String name, RentalManagerImpl rentalManager) {
        String email = name.toLowerCase().replace(" ", "") + "@example.com"; // Simplified email generation
        Owner newOwner = new Owner(String.valueOf(++ownerIdCounter), name, "1970-01-01", email); // Modify the constructor if necessary
        rentalManager.addOwner(newOwner);
        updateCsv("data/owners.csv", newOwner);
        System.out.println("Created new owner: " + name);
        return newOwner;
    }

    public static Property promptForProperty(RentalManagerImpl rentalManager) {
        System.out.print("Enter property address: ");
        String propertyAddress = scanner.nextLine();
        return Stream.concat(rentalManager.getAllResidentialProperties().stream(),
                        rentalManager.getAllCommercialProperties().stream())
                .filter(p -> p.getAddress().equals(propertyAddress))
                .findFirst()
                .orElseGet(() -> createProperty(propertyAddress, rentalManager));
    }

    private static Property createProperty(String address, RentalManagerImpl rentalManager) {
        Property newProperty = new Property("P" + propertyIdCounter++, address, 250000.0, "Available"); // Modify the constructor if necessary
        rentalManager.addProperty(newProperty);
        updateCsv("data/properties.csv", newProperty);
        System.out.println("Created new property: " + address);
        return newProperty;
    }

    private static void updateCsv(String fileName, Object entity) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            if (entity instanceof Tenant tenant) {
                writer.append(String.join(",", tenant.getId(), tenant.getFullName(), tenant.getDateOfBirth().toString(), tenant.getContactInformation())).append("\n");
            } else if (entity instanceof Host host) {
                writer.append(String.join(",", host.getId(), host.getFullName(), host.getDateOfBirth().toString(), host.getContactInformation())).append("\n");
            } else if (entity instanceof Owner owner) {
                writer.append(String.join(",", owner.getId(), owner.getFullName(), owner.getDateOfBirth().toString(), owner.getContactInformation())).append("\n");
            } else if (entity instanceof Property property) {
                writer.append(String.join(",", property.getPropertyId(), property.getAddress(), String.valueOf(property.getPricing()), property.getStatus())).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error updating " + fileName + ": " + e.getMessage());
        }
    }
}