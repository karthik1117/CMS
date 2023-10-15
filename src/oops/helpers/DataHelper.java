package oops.helpers;

import oops.Customer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataHelper {
    static final String CUSTOMER_DATA_FILE = "customer_data.txt";
    private static final Logger logger = Logger.getLogger(DataHelper.class.getName());

    public static Map<UUID, Customer> loadCustomers() {
        File file = new File(CUSTOMER_DATA_FILE);

        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CUSTOMER_DATA_FILE))) {
                Map<UUID, Customer> customersMap = (Map<UUID, Customer>) inputStream.readObject();
                return customersMap;
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                logger.log(Level.SEVERE, "Error loading customer data", e);
                return new HashMap<>();
            }
        } else {
            // Initialize with initial data and save to the file
            Map<UUID, Customer> initialCustomers = createInitialCustomerData();
            saveCustomers(initialCustomers);
            return initialCustomers;
        }
    }


    private static Map<UUID, Customer> createInitialCustomerData() {
        Map<UUID, Customer> initialCustomers = new HashMap<>();

        Customer customer1 = new Customer("John", "123 Main St", "123-456-7890");
        initialCustomers.put(customer1.getCustomerId(), customer1);

        Customer customer2 = new Customer("Alice", "456 Elm St", "987-654-3210");
        initialCustomers.put(customer2.getCustomerId(), customer2);

        return initialCustomers;
    }

     public static void saveCustomers(Map<UUID, Customer> customers) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CUSTOMER_DATA_FILE))) {
            outputStream.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCustomerData(Customer customer) {
        Map<UUID, Customer> customers = loadCustomers();
        customers.put(customer.getCustomerId(), customer);
        saveCustomers(customers);
    }
}
