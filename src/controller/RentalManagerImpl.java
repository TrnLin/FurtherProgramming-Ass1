/** 
* @author Tran Hoang Linh - S03097 
*/ 

package controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import model.*;


public class RentalManagerImpl implements RentalManager {
    private final List<RentalAgreement> rentalAgreements = new ArrayList<>();
    private final List<Tenant> tenants = new ArrayList<>();
    private final List<Host> hosts = new ArrayList<>();
    private final List<Owner> owners = new ArrayList<>();
    private final List<ResidentialProperty> residentialProperties = new ArrayList<>();
    private final List<CommercialProperty> commercialProperties = new ArrayList<>();
    private final List<Payment> payments = new ArrayList<>();
    private final List<Property> properties = new ArrayList<>();

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    // File paths
    private static final String DATA_FOLDER = "data/";
    private static final String TENANTS_FILE = DATA_FOLDER + "tenants.csv";
    private static final String HOSTS_FILE = DATA_FOLDER + "hosts.csv";
    private static final String OWNERS_FILE = DATA_FOLDER + "owners.csv";
    private static final String RESIDENTIAL_PROPERTIES_FILE = DATA_FOLDER + "residential_properties.csv";
    private static final String COMMERCIAL_PROPERTIES_FILE = DATA_FOLDER + "commercial_properties.csv";
    private static final String RENTAL_AGREEMENTS_FILE = DATA_FOLDER + "rental_agreements.csv";
    private static final String PAYMENTS_FILE = DATA_FOLDER + "payments.csv";
    private static final String PROPERTIES_FILE = DATA_FOLDER + "properties.csv";

    private static final Logger LOGGER = Logger.getLogger(RentalManagerImpl.class.getName());


    @Override
    public void addRentalAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }

    //Update rental agreement
    @Override
    public void updateRentalAgreement(String agreementId, RentalAgreement updatedAgreement) {
        try {
            Optional<RentalAgreement> existingAgreement = rentalAgreements.stream()
                    .filter(agreement -> agreement.getId().equals(agreementId))
                    .findFirst();

            if (existingAgreement.isPresent()) {
                rentalAgreements.remove(existingAgreement.get());
                rentalAgreements.add(updatedAgreement);
            } else {
                LOGGER.warning("Rental Agreement with ID " + agreementId + " not found.");
            }
        } catch (Exception e) {
            LOGGER.severe("Error updating rental agreement: " + e.getMessage());
        }
    }

    //Delete rental agreement
    @Override
    public void deleteRentalAgreement(String agreementId) {
        rentalAgreements.removeIf(agreement -> agreement.getAgreementId().equals(agreementId));
    }

    @Override
    public List<RentalAgreement> getAllRentalAgreements() {
        return rentalAgreements;
    }

    @Override
    public List<RentalAgreement> getRentalAgreementsByOwnerName(String ownerName) {
        return rentalAgreements.stream()
                .filter(agreement -> agreement.getOwner().getFullName().toLowerCase().contains(ownerName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalAgreement> getRentalAgreementsByPropertyAddress(String propertyAddress) {
        return rentalAgreements.stream()
                .filter(agreement -> agreement.getProperty() != null && agreement.getProperty().getAddress().toLowerCase().contains(propertyAddress.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalAgreement> getRentalAgreementsByStatus(String status) {
        return rentalAgreements.stream()
                .filter(agreement -> agreement.getStatus() != null && agreement.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tenant> getAllTenants() {
        return tenants;
    }

    @Override
    public List<Host> getAllHosts() {
        return hosts;
    }

    @Override
    public List<Owner> getAllOwners() {
        return owners;
    }

    @Override
    public List<ResidentialProperty> getAllResidentialProperties() {
        return residentialProperties;
    }

    @Override
    public List<CommercialProperty> getAllCommercialProperties() {
        return commercialProperties;
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public List<Property> getAllProperties() {
        return properties;
    }

    public List<RentalAgreement> getSortedRentalAgreements(Comparator<RentalAgreement> comparator) {
        return rentalAgreements.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Payment> getSortedPayments(Comparator<Payment> comparator) {
        return payments.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Tenant> getSortedTenants(Comparator<Tenant> comparator) {
        return tenants.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Host> getSortedHosts(Comparator<Host> comparator) {
        return hosts.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Owner> getSortedOwners(Comparator<Owner> comparator) {
        return owners.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Property> getSortedProperties(Comparator<Property> comparator) {
        return properties.stream().sorted(comparator).collect(Collectors.toList());
    }


    // Load sample data
    public void loadSampleData() {
        try {
            loadTenantsFromFile(TENANTS_FILE);
            loadHostsFromFile(HOSTS_FILE);
            loadOwnersFromFile(OWNERS_FILE);
            loadResidentialPropertiesFromFile(RESIDENTIAL_PROPERTIES_FILE);
            loadCommercialPropertiesFromFile(COMMERCIAL_PROPERTIES_FILE);
            loadRentalAgreementsFromFile(RENTAL_AGREEMENTS_FILE);
            loadPaymentsFromFile(PAYMENTS_FILE);
            loadPropertiesFromFile(PROPERTIES_FILE);
        } catch (IOException | ParseException e) {
            System.err.println("Error loading sample data: " + e.getMessage());
        }
    }

    // Save data
    public void saveData() {
        try {
            saveTenantsToFile(TENANTS_FILE);
            saveHostsToFile(HOSTS_FILE);
            saveOwnersToFile(OWNERS_FILE);
            saveResidentialPropertiesToFile(RESIDENTIAL_PROPERTIES_FILE);
            saveCommercialPropertiesToFile(COMMERCIAL_PROPERTIES_FILE);
            saveRentalAgreementsToFile(RENTAL_AGREEMENTS_FILE);
            savePaymentsToFile(PAYMENTS_FILE);
            savePropertiesToFile(PROPERTIES_FILE);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadTenantsFromFile(String filepath) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                Tenant tenant = new Tenant(parts[0], parts[1], dateFormat.parse(parts[2]), parts[3]);
                tenants.add(tenant);
            }
        }
        reader.close();
    }

    private void loadHostsFromFile(String filepath) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                Host host = new Host(parts[0], parts[1], dateFormat.parse(parts[2]), parts[3]);
                hosts.add(host);
            }
        }
        reader.close();
    }

    private void loadOwnersFromFile(String filepath) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                Owner owner = new Owner(parts[0], parts[1], dateFormat.parse(parts[2]), parts[3]);
                owners.add(owner);
            }
        }
        reader.close();
    }

    private void loadResidentialPropertiesFromFile(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 7) {
                ResidentialProperty property = new ResidentialProperty(
                        parts[0], parts[1], Double.parseDouble(parts[2]), parts[3],
                        Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[5]), Boolean.parseBoolean(parts[6])
                );
                residentialProperties.add(property);
            }
        }
        reader.close();
    }

    private void loadCommercialPropertiesFromFile(String filepath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                CommercialProperty property = new CommercialProperty(
                        fields[0], fields[1], Double.parseDouble(fields[2]), fields[3],
                        fields[4], Integer.parseInt(fields[5]), Double.parseDouble(fields[6])
                );
                commercialProperties.add(property);


            }
        }
    }


    //done
    private void loadRentalAgreementsFromFile(String filepath) throws IOException, ParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Basic validation to ensure parts contains enough data
                if (parts.length < 9) {
                    System.err.println("Malformed line: " + line);
                    continue;
                }

                String agreementId = parts[0].trim();

                List<Tenant> tenants = Arrays.stream(parts[1].split(";"))
                        .map(name -> new Tenant(name.trim()))  // Populating tenant with minimal function, add more details if necessary
                        .collect(Collectors.toList());

                List<Host> hosts = Arrays.stream(parts[2].split(";"))
                        .map(name -> new Host(name.trim()))  // Populating host with minimal function, add more details if necessary
                        .collect(Collectors.toList());

                Owner owner = new Owner(parts[3].trim());
                ResidentialProperty property = new ResidentialProperty(parts[4].trim());
                Date contractDate = dateFormat.parse(parts[5].trim());
                String period = parts[6].trim();
                double rentingFee = Double.parseDouble(parts[7].trim());
                String status = parts[8].trim();

                RentalAgreement agreement = new RentalAgreement(agreementId, tenants, hosts, owner, property, contractDate, period, rentingFee, status);
                rentalAgreements.add(agreement);
            }
        }


    }

    private void loadPaymentsFromFile(String filepath) throws IOException, ParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 6) {
                    System.err.println("Malformed line: " + line);
                    continue;
                }
                String paymentId = parts[0].trim();
                String agreementId = parts[1].trim();
                Tenant tenant = findTenantByName(parts[2].trim());
                double amount = Double.parseDouble(parts[3].trim());
                Date paymentDate = dateFormat.parse(parts[4].trim());
                String paymentMethod = parts[5].trim();
                String status = parts[6].trim();
                Payment payment = new Payment(paymentId, agreementId, tenant, amount, paymentDate, paymentMethod, status);
                payments.add(payment);
            }
        }
    }

    private void loadPropertiesFromFile(String filepath) throws IOException, ParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.err.println("Malformed line: " + line);
                    continue;
                }
                String propertyId = parts[0].trim();
                String address = parts[1].trim();
                double pricing = Double.parseDouble(parts[2].trim());
                String status = parts[3].trim();
                Property property = new Property(propertyId, address, pricing, status);
                properties.add(property);
            }

        }

    }

    private void savePropertiesToFile(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (Property property : properties) {
            writer.write(String.format("%s,%s,%f,%s%n",
                    property.getPropertyId(), property.getAddress(), property.getPricing(), property.getStatus()));
        }
        writer.close();
    }


    private void savePaymentsToFile(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (Payment payment : payments) {
            writer.write(String.format("%s,%s,%s,%f,%s,%s,%s%n",
                    payment.getPaymentId(), payment.getAgreementId(), payment.getTenant().getFullName(),
                    payment.getAmount(), dateFormat.format(payment.getPaymentDate()), payment.getPaymentMethod(), payment.getStatus()));
        }
        writer.close();
    }

    private void saveTenantsToFile(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (Tenant tenant : tenants) {
            writer.write(String.format("%s,%s,%s,%s%n",
                    tenant.getId(), tenant.getFullName(), dateFormat.format(tenant.getDateOfBirth()), tenant.getContactInformation()));
        }
        writer.close();
    }

    private void saveHostsToFile(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (Host host : hosts) {
            writer.write(String.format("%s,%s,%s,%s%n",
                    host.getId(), host.getFullName(), dateFormat.format(host.getDateOfBirth()), host.getContactInformation()));
        }
        writer.close();
    }

    private void saveOwnersToFile(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (Owner owner : owners) {
            writer.write(String.format("%s,%s,%s,%s%n",
                    owner.getId(), owner.getFullName(), dateFormat.format(owner.getDateOfBirth()), owner.getContactInformation()));
        }
        writer.close();
    }

    private void saveResidentialPropertiesToFile(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (ResidentialProperty property : residentialProperties) {
            writer.write(String.format("%s,%s,%f,%s,%d,%b,%b%n",
                    property.getPropertyId(), property.getAddress(), property.getPricing(), property.getStatus(),
                    property.getNumberOfBedrooms(), property.isHasGarden(), property.isPetFriendly()));
        }
        writer.close();
    }

    private void saveCommercialPropertiesToFile(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (CommercialProperty property : commercialProperties) {
            writer.write(String.format("%s,%s,%f,%s,%s,%d,%f%n",
                    property.getPropertyId(), property.getAddress(), property.getPricing(), property.getStatus(),
                    property.getBusinessType(), property.getParkingSpaces(), property.getSquareFootage()));
        }
        writer.close();
    }

    //done
    private void saveRentalAgreementsToFile(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (RentalAgreement agreement : rentalAgreements) {
            String tenantNames = agreement.getTenants().stream().map(Tenant::getFullName).collect(Collectors.joining(";"));
            String hostNames = agreement.getHosts().stream().map(Host::getFullName).collect(Collectors.joining(";"));
            writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%f,%s%n",
                    agreement.getAgreementId(), tenantNames, hostNames, agreement.getOwner().getFullName(),
                    agreement.getProperty().getAddress(), dateFormat.format(agreement.getContractDate()),
                    agreement.getPeriod(), agreement.getRentingFee(), agreement.getStatus()));
        }
        writer.close();
    }

    private Tenant findTenantByName(String fullName) {
        return tenants.stream().filter(t -> t.getFullName().equals(fullName)).findFirst().orElse(null);
    }

    public void addProperty(Property newProperty) {
        properties.add(newProperty);
    }

    public void addTenant(Tenant newTenant) {
        tenants.add(newTenant);
    }

    public void addHost(Host newHost) {
        hosts.add(newHost);
    }

    public void addOwner(Owner newOwner) {
        owners.add(newOwner);
    }

    public void addResidentialProperty(ResidentialProperty newProperty) {
        residentialProperties.add(newProperty);
    }

    public void addCommercialProperty(CommercialProperty newProperty) {
        commercialProperties.add(newProperty);
    }

    public void addPayment(Payment newPayment) {
        payments.add(newPayment);
    }

    public void deleteTenant(Tenant tenant) {
        tenants.remove(tenant);
    }

    public void deleteHost(Host host) {
        hosts.remove(host);
    }

    public void deleteOwner(Owner owner) {
        owners.remove(owner);
    }

    public void deleteResidentialProperty(ResidentialProperty property) {
        residentialProperties.remove(property);
    }

    public void deleteCommercialProperty(CommercialProperty property) {
        commercialProperties.remove(property);
    }

    public void deletePayment(Payment payment) {
        payments.remove(payment);
    }

    public void deleteProperty(Property property) {
        properties.remove(property);
    }


}