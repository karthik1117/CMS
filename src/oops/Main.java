package oops;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import static oops.helpers.DataHelper.loadCustomers;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<UUID, Customer> customers = loadCustomers();

        boolean exitProgram = false;

        while (!exitProgram) {
            int userType;

            System.out.println("Select User Type:");
            System.out.println("1. Bank Administrator");
            System.out.println("2. Customer");
            try {
                userType = scanner.nextInt();
                switch (userType) {
                    case 1 -> {
                        BankAdministrator admin = new BankAdministrator(scanner, customers);
                        admin.performOperations();
                    }
                    case 2 -> handleCustomer(scanner, customers);
                    default -> System.out.println("Invalid user type.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid values.");
                scanner.next();
            }

            System.out.println("Do you want to continue? (1: Yes / 2: No)");
            int exitChoice = scanner.nextInt();
            if (exitChoice == 2) {
                exitProgram = true;
            }
        }

        scanner.close();
    }


    private static void handleCustomer(Scanner scanner, Map<UUID, Customer> customers) {
        boolean validCustomerIDEntered = false;

        while (!validCustomerIDEntered) {
            System.out.print("Enter your Customer ID (or type 'exit' to return to the main menu): ");
            String input = scanner.next(); //1a57139e-7d35-4ac3-b23d-41fba9533b7e

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            UUID customerID = null;

            try {
                customerID = UUID.fromString(input);
                Customer customer = customers.get(customerID);

                if (customer == null) {
                    System.out.println("Customer not found. Please check your Customer ID.");
                } else {
                    CustomerManager customerManager = new CustomerManager(scanner, customer, customers);
                    customerManager.performOperations();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid UUID format. Please enter a valid UUID.");
            }
        }
    }
}
