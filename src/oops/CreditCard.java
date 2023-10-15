package oops;

import java.io.Serializable;
import java.util.UUID;

public class CreditCard implements Serializable {
    private String creditCardNumber;
    private int cvv;
    private UUID customerId;
    private double spendingLimit;
    private double balance;
    private String creditCardType;
    private String status; // Status of the credit card (e.g., "Open", "Closed", "Blocked")

    public CreditCard(String creditCardNumber, int cvv, String creditCardType) {
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.customerId = customerId;
        this.creditCardType = creditCardType;
        this.spendingLimit = determineSpendingLimit(creditCardType);
        this.balance = spendingLimit;
        this.status = "Open";
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public double getSpendingLimit() {
        return spendingLimit;
    }

    public double getBalance() {
        return balance;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to make a purchase using the credit card
    public boolean makePurchase(double amount) {
        if (amount <= spendingLimit - balance) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    // Method to make a payment towards the credit card balance
    public void makePayment(double paymentAmount) {
        balance -= paymentAmount;
    }

    // Method to determine spending limit based on card type
    private double determineSpendingLimit(String cardType) {
        if ("Basic".equals(cardType)) {
            return 1000.0;
        } else if ("Gold".equals(cardType)) {
            return 5000.0;
        } else if ("Platinum".equals(cardType)) {
            return 10000.0;
        } else {
            return 0.0;
        }
    }



}

