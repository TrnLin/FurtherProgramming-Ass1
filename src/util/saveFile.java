package util;

import model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class saveFile {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static void updateCsv(String fileName, Object entity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            if (entity instanceof Tenant) {
                updateTenantCsv(writer, (Tenant) entity);
            } else if (entity instanceof Owner) {
                updateOwnerCsv(writer, (Owner) entity);
            } else if (entity instanceof Host) {
                updateHostCsv(writer, (Host) entity);
            } else if (entity instanceof Property) {
                updatePropertyCsv(writer, (Property) entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTenantsCsv(String fileName, List<Tenant> tenants) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Tenant tenant : tenants) {
                writer.write(formatTenant(tenant));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateOwnersCsv(String fileName, List<Owner> owners) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Owner owner : owners) {
                writer.write(formatOwner(owner));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateHostsCsv(String fileName, List<Host> hosts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Host host : hosts) {
                writer.write(formatHost(host));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updatePropertyCsv(String fileName, List<Property> properties) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Property property : properties) {
                writer.write(formatProperty(property));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateTenantCsv(BufferedWriter writer, Tenant tenant) throws IOException {
        writer.write(formatTenant(tenant));
    }

    private static void updateOwnerCsv(BufferedWriter writer, Owner owner) throws IOException {
        writer.write(formatOwner(owner));
    }

    private static void updateHostCsv(BufferedWriter writer, Host host) throws IOException {
        writer.write(formatHost(host));
    }

    private static void updatePropertyCsv(BufferedWriter writer, Property property) throws IOException {
        writer.write(formatProperty(property));
    }

    private static String formatTenant(Tenant tenant) {
        return String.join(",",
                tenant.getId(),
                tenant.getFullName(),
                dateFormat.format(tenant.getDateOfBirth()),
                tenant.getContactInformation()
        );
    }

    private static String formatOwner(Owner owner) {
        return String.join(",",
                owner.getId(),
                owner.getFullName(),
                dateFormat.format(owner.getDateOfBirth()),
                owner.getContactInformation()
        );
    }

    private static String formatHost(Host host) {
        return String.join(",",
                host.getId(),
                host.getFullName(),
                dateFormat.format(host.getDateOfBirth()),
                host.getContactInformation()
        );
    }

    private static String formatProperty(Property property) {
        return String.join(",",
                property.getPropertyId(),
                property.getAddress(),
                Double.toString(property.getPricing()),
                property.getStatus()
        );
    }
}