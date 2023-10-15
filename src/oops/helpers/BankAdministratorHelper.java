package oops.helpers;

import oops.CreditCard;
import oops.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static oops.helpers.DataHelper.saveCustomerData;
import static oops.helpers.DataHelper.saveCustomers;


public class BankAdministratorHelper {
    public static void viewAllCustomersData(Map<UUID, Customer> customers) {
        try {
            if (customers.isEmpty()) {
                System.out.println("No customer data available.");
            } else {
                System.out.println("All Customers Data:");
                for (Customer customer : customers.values()) {
                    System.out.println("Customer ID: " + customer.getCustomerId());
                    System.out.println("Name: " + customer.getName());
                    System.out.println("Address: " + customer.getAddress());
                    System.out.println("Phone: " + customer.getPhone());

                    List<CreditCard> creditCards = customer.getCreditCards();
                    if (creditCards.isEmpty()) {
                        System.out.println("Credit Cards: None");
                    } else {
                        System.out.println("Credit Cards:");
                        for (CreditCard creditCard : creditCards) {
                            System.out.println("    Card Number: " + creditCard.getCreditCardNumber());
                            System.out.println("    CVV: " + creditCard.getCvv());
                            System.out.println("    Spending Limit: " + creditCard.getSpendingLimit());
                            System.out.println("    Balance: " + creditCard.getBalance());
                            System.out.println("    Card Type: " + creditCard.getCreditCardType());
                            System.out.println("    Card Status: " + creditCard.getStatus());
                        }
                    }

                    System.out.println("---------------------------------------");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Error loading customer data.");
        }
    }


    public static void viewAllIssuedCards(Map<UUID, Customer> customers) {
        long creditCardCount = 1;
        if (customers.isEmpty()) {
            System.out.println("No customer data available.");
        } else {
            System.out.println("All Issued Cards:");
            for (Customer customer : customers.values()) {
                List<CreditCard> creditCards = customer.getCreditCards();
                if (!creditCards.isEmpty()) {
                    for (CreditCard creditCard : creditCards) {
                        System.out.println(creditCardCount + ". " + creditCard.getCreditCardNumber());
                        creditCardCount++;
                    }
                }
                System.out.println("---------------------------------------");
            }

            if (creditCardCount == 1) {
                System.out.println("No credit cards found.");
            }
        }
    }

    public static void addNewCustomer(Map<UUID, Customer> customers, Scanner scanner) {
        try {
            System.out.print("Enter customer name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            System.out.print("Enter customer address: ");
            String address = scanner.nextLine();

            System.out.print("Enter customer phone number: ");
            String phoneNumber = scanner.next();


            Customer newCustomer = new Customer(name, address, phoneNumber);
            UUID customerID = UUID.randomUUID();
            customers.put(customerID, newCustomer);
            saveCustomers(customers);

            System.out.println("New customer added successfully with ID: " + customerID);
            System.out.println("---------------------------------------");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Consume the invalid input
        }
    }


    public void issueNewCreditCard(Map<UUID, Customer> customers, Scanner scanner) {
        try {
            System.out.print("Enter the Customer ID: ");
            String input = scanner.next();
            UUID customerID = UUID.fromString(input);
            Customer customer = customers.get(customerID);

            if (customer == null) {
                System.out.println("Customer not found. Please check the Customer ID.");
                return;
            } else {
                CommonUtils.addNewCreditCard(customer, customers, scanner);
            }
            System.out.println("---------------------------------------");
        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
        }
    }

    public static void viewBlockedCards(Map<UUID, Customer> customers) {
        long blockedCardCount = 1;
        if (customers.isEmpty()) {
            System.out.println("No customer data available.");
        } else {
            System.out.println("All Blocked Credit Cards:");
            for (Customer customer : customers.values()) {
                List<CreditCard> creditCards = customer.getCreditCards();
                if (!creditCards.isEmpty()) {
                    for (CreditCard creditCard : creditCards) {
                        String creditCardStatus = creditCard.getStatus();
                        if (creditCardStatus.equalsIgnoreCase("Closed") || creditCardStatus.equalsIgnoreCase("Blocked")) {
                            System.out.println(blockedCardCount + ". " + creditCard.getCreditCardNumber());
                            blockedCardCount++;
                        }
                    }
                }
                System.out.println("---------------------------------------");
            }
            if (blockedCardCount == 1){
                System.out.println("No blocked credit cards found.");
                System.out.println("---------------------------------------");
            }
        }
    }

    public void saveBlockedCardsToFileForAdmin(Map<UUID, Customer> customers, String filePath) {
        long blockedCardCount = 1;
        try (FileWriter writer = new FileWriter(filePath)) {
            if (customers.isEmpty()) {
                writer.write("No customer data available.\n");
            } else {
                writer.write("All Blocked Credit Cards:\n");
                for (Customer customer : customers.values()) {
                    List<CreditCard> creditCards = customer.getCreditCards();
                    if (!creditCards.isEmpty()) {
                        for (CreditCard creditCard : creditCards) {
                            String creditCardStatus = creditCard.getStatus();
                            if (creditCardStatus.equalsIgnoreCase("Closed") || creditCardStatus.equalsIgnoreCase("Blocked")) {
                                writer.write(blockedCardCount + ". " + creditCard.getCreditCardNumber() + "\n");
                                blockedCardCount++;
                            }
                        }
                    }
                    writer.write("---------------------------------------\n");
                }
                if (blockedCardCount == 1){
                    writer.write("No blocked credit cards found.\n");
                    writer.write("---------------------------------------\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeOrBlockCreditCard(Customer customer, String creditCardNumber) {
        List<CreditCard> creditCards = customer.getCreditCards();
        if (!creditCards.isEmpty()) {
            boolean cardFound = false;

            for (CreditCard creditCard : creditCards) {
                String cardStatus = creditCard.getStatus();
                String cardNo = creditCard.getCreditCardNumber();

                if (creditCardNumber.equals(cardNo)) {
                    cardFound = true;

                    if (cardStatus.equalsIgnoreCase("Closed") || cardStatus.equalsIgnoreCase("Blocked")) {
                        System.out.println("Credit card is already in a blocked state.");
                    } else {
                        creditCard.setStatus("Closed");
                        saveCustomerData(customer);
                        System.out.println("Credit card blocked successfully.");
                    }
                }
            }

            if (!cardFound) {
                System.out.println("Credit card not found in the customer's account.");
            }
        } else {
            System.out.println("No credit cards available.");
        }
    }
}

