package oops.helpers;

import oops.CreditCard;
import oops.Customer;

import java.util.*;

import static oops.helpers.DataHelper.*;



public class CustomerOperationsHelper {
    private Scanner scanner;
    private Customer customer;
    public CustomerOperationsHelper(Scanner scanner, Customer customer) {
        this.scanner = scanner;
        this.customer = customer;
    }

    public void viewBalance() {
        try {
            System.out.println("Credit Cards Available in Your Account:");

            List<CreditCard> creditCards = customer.getCreditCards();

            if (creditCards.isEmpty()) {
                System.out.println("No credit cards available.");
                return;
            }

            for (int i = 0; i < creditCards.size(); i++) {
                CreditCard creditCard = creditCards.get(i);
                System.out.println((i + 1) + ". Card Number: " + creditCard.getCreditCardNumber());
            }

            System.out.print("Select a Credit Card (enter the number): ");

            int maxAttempts = 3;
            int attempts = 0;
            int cardChoice = 0;
            while (attempts < maxAttempts) {
                System.out.print("Select a Credit Card (enter the number): ");
                cardChoice = scanner.nextInt();

                if (cardChoice >= 1 && cardChoice <= creditCards.size()) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please select a valid credit card.");
                    attempts++;
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Maximum number of attempts reached. Exiting program.");
                return;
            }

            CreditCard selectedCard = creditCards.get(cardChoice - 1);

            System.out.print("Enter the CVV: ");
            int cvv = scanner.nextInt();

            if (selectedCard.getCvv() == cvv) {
                System.out.println("You can spend up to " + selectedCard.getBalance() + " this month!");
            } else {
                System.out.println("Invalid CVV. Please try again.");
            }
            System.out.println("---------------------------------------");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
        }
    }


    public void closeOrBlockCreditCard() {
        try {
            if (customer.getCreditCards().isEmpty()) {
                System.out.println("You don't have any credit cards in your account.");
                return;
            }

            System.out.print("Enter the credit card number to close/block: ");
            String creditCardNumber = scanner.next();

            boolean creditCardFound = false;

            for (CreditCard creditCard : customer.getCreditCards()) {
                if (creditCard.getCreditCardNumber().equals(creditCardNumber)) {
                    creditCard.setStatus("Closed");
                    saveCustomerData(customer);
                    System.out.println("Credit card " + creditCardNumber + " closed/blocked successfully.");
                    creditCardFound = true;
                    break;
                }
            }

            if (!creditCardFound) {
                System.out.println("Credit card " + creditCardNumber + " not found in your account.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
        }
    }

}
