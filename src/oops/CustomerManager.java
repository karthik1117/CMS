package oops;

import oops.helpers.CommonUtils;
import oops.helpers.CustomerOperationsHelper;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

class CustomerManager extends User {

    private CustomerOperationsHelper operationsHelper;
    private Customer customer;
    private Map<UUID, Customer> customers;


    public CustomerManager(Scanner scanner, Customer customer, Map<UUID, Customer> customers) {
        super(scanner);
        this.customer = customer;
        this.customers = customers;
        this.operationsHelper = new CustomerOperationsHelper(scanner, customer);
    }

    @Override
    public void performOperations() {
        try {
            boolean logout = false;

            while (!logout) {
                System.out.println("Hello Customer!");
                System.out.println("Select the operation to perform:");
                System.out.println("1. Apply for new credit card");
                System.out.println("2. View Balance");
                System.out.println("3. Close/block credit card");
                System.out.println("4. Logout");

                int operation = scanner.nextInt();

                switch (operation) {
                    case 1 -> CommonUtils.addNewCreditCard(customer, customers, scanner);
                    case 2 -> operationsHelper.viewBalance();
                    case 3 -> operationsHelper.closeOrBlockCreditCard();
                    case 4 -> logout = true;
                    default -> System.out.println("Invalid operation.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
        }
    }
}

