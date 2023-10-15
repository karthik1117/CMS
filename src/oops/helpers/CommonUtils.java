package oops.helpers;

import oops.CreditCard;
import oops.Customer;

import java.util.*;

import static oops.helpers.DataHelper.saveCustomerData;

public class CommonUtils {
    // Maximum number of credit cards each customer can have.
    public static final int MAX_CREDIT_CARDS = 5;
    public static void addNewCreditCard(Customer customer, Map<UUID, Customer> customers, Scanner scanner) {
        try {
            if (customer.getCreditCards().size() < 5) {
                System.out.println("Applying for a new credit card.");
                System.out.println("Available Credit Card Types:");
                System.out.println("1. Gold");
                System.out.println("2. Platinum");
                System.out.println("3. Diamond");

                int choice;
                String selectedCardType = "Gold"; // Default to Basic if the choice is invalid

                while (true) {
                    System.out.print("Choose a Credit Card Type (1/2/3): ");
                    try {
                        choice = scanner.nextInt();
                        if (choice >= 1 && choice <= 3) {
                            break; // Exit the loop if choice is valid
                        } else {
                            System.out.println("Invalid choice. Please enter a valid option.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }

                String creditCardNumber;
                while (true) {
                    System.out.print("Enter the credit card number (16 digits): ");
                    creditCardNumber = scanner.next();
                    if (creditCardNumber.matches("\\d{16}")) {
                        if (isCreditCardNumberUnique(customers, creditCardNumber)) {
                            break; // Exit the loop if valid and unique
                        } else {
                            System.out.println("This credit card number is already in use.");
                        }
                    } else {
                        System.out.println("Invalid credit card number. Please enter a 16-digit number.");
                    }
                }

                int cvv;
                while (true) {
                    System.out.print("Enter the 4-digit CVV : ");
                    try {
                        cvv = scanner.nextInt();
                        if (cvv >= 1000 && cvv <= 9999) {
                            break;
                        } else {
                            System.out.println("Invalid CVV. Please enter a 4-digit number.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid 4-digit number.");
                        scanner.next();
                    }
                }

                CreditCard newCard = new CreditCard(creditCardNumber, cvv, selectedCardType);

                customer.addCreditCard(newCard);

                saveCustomerData(customer);

                System.out.println("New credit card added successfully. Card Number: " + creditCardNumber);
            } else {
                System.out.println("Sorry, you already have the maximum number of credit cards.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
        }
    }

    public static boolean isCreditCardNumberUnique(Map<UUID, Customer> customers, String creditCardNumber) {
        for (Customer customer : customers.values()) {
            List<CreditCard> customerCreditCards = customer.getCreditCards();
            for (CreditCard card : customerCreditCards) {
                if (creditCardNumber.equals(card.getCreditCardNumber())) {
                    return false; // Credit card number is not unique
                }
            }
        }
        return true; // Credit card number is unique
    }
}
