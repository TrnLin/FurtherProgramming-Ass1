package controller;

import model.Host;
import model.Owner;
import model.Property;
import model.Tenant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prompt {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    public static List<Tenant> promptForTenants(RentalManagerImpl rentalManager) {
        System.out.print("Enter tenant names (semicolon separated): ");
        String tenantNames = scanner.nextLine();
        return Stream.of(tenantNames.split(";"))
                .map(name -> rentalManager.getAllTenants().stream()
                        .filter(t -> t.getFullName().equals(name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Tenant not found: " + name)))
                .collect(Collectors.toList());
    }

    public static List<Host> promptForHosts(RentalManagerImpl rentalManager) {
        System.out.print("Enter host names (semicolon separated): ");
        String hostNames = scanner.nextLine();
        return Stream.of(hostNames.split(";"))
                .map(name -> rentalManager.getAllHosts().stream()
                        .filter(h -> h.getFullName().equals(name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Host not found: " + name)))
                .collect(Collectors.toList());
    }

    public static Owner promptForOwner(RentalManagerImpl rentalManager) {
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();
        return rentalManager.getAllOwners().stream()
                .filter(o -> o.getFullName().equals(ownerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Owner not found: " + ownerName));
    }

    public static Property promptForProperty(RentalManagerImpl rentalManager) {
        System.out.print("Enter property address: ");
        String propertyAddress = scanner.nextLine();
        return Stream.concat(rentalManager.getAllResidentialProperties().stream(),
                        rentalManager.getAllCommercialProperties().stream())
                .filter(p -> p.getAddress().equals(propertyAddress))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Property not found: " + propertyAddress));
    }
}
