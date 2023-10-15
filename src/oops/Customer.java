package oops;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer implements Serializable {
    private UUID customerId;
    private String name;
    private String address;
    private String phone;
    private List<CreditCard> creditCards;

    public Customer(String name, String address, String phone) {
        this.customerId = UUID.randomUUID(); // Generate a unique customer ID
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.creditCards = new ArrayList<>(5);
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
    }

    // Method to remove a credit card from the customer
    public void removeCreditCard(CreditCard creditCard) {
        creditCards.remove(creditCard);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", creditCards=" + creditCards +
                '}';
    }
}
