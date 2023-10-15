package oops;

import oops.helpers.BankAdministratorHelper;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;


class BankAdministrator extends User {
    private BankAdministratorHelper bankAdministratorHelper;
    private Map<UUID, Customer> customers;

    public BankAdministrator(Scanner scanner, Map<UUID, Customer> customers) {
        super(scanner);
        this.bankAdministratorHelper = new BankAdministratorHelper();
        this.customers = customers;
    }

    @Override
    public void performOperations() {
        boolean logout = false;

        while (!logout) {
            System.out.println("Hello Bank Administrator!");
            System.out.println("Select the operation to perform:");
            System.out.println("1. View all Customers data");
            System.out.println("2. View all issued cards");
            System.out.println("3. Add new Customer");
            System.out.println("4. Issue new credit card");
            System.out.println("5. View blocked cards");
            System.out.println("6. Close/block credit card");
            System.out.println("7. Logout");

            int operation = scanner.nextInt();

            switch (operation) {
                case 1 -> bankAdministratorHelper.viewAllCustomersData(customers);
                case 2 -> bankAdministratorHelper.viewAllIssuedCards(customers);
                case 3 -> bankAdministratorHelper.addNewCustomer(customers, scanner);
                case 4 -> bankAdministratorHelper.issueNewCreditCard(customers, scanner);
                case 5 -> {
                    String filePath = "blocked_credit_cards.txt";
                    bankAdministratorHelper.saveBlockedCardsToFileForAdmin(customers, filePath);
                    bankAdministratorHelper.viewBlockedCards(customers);
                }
                case 6 -> {
                    UUID customerID = null;
                    String creditCardNumber = null;
                    Customer customer = null;
                    try {
                        System.out.print("Enter your Customer ID (or type 'exit' to return to the main menu): ");
                        String input = scanner.next();
                        customerID = UUID.fromString(input);
                        customer = customers.get(customerID);
                        if (customer == null) {
                            System.out.println("Customer not found. Please check your Customer ID.");
                        } else {
                            System.out.print("Enter the credit card number to close/block: ");
                            creditCardNumber = scanner.next();
                            bankAdministratorHelper.closeOrBlockCreditCard(customer, creditCardNumber);
                        }
                    } catch (IllegalArgumentException | InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid input.");
                    }
                }
                case 7 -> {
                    logout = true;
                    System.out.println("Logged out. Returning to the main menu.");
                    System.out.println();
                }
                default -> System.out.println("Invalid operation.");
            }
        }
    }
}

