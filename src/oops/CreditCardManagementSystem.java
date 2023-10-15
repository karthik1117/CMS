package oops;

import java.util.Scanner;

public class CreditCardManagementSystem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Display the menu
        System.out.println("Select the user type:");
        System.out.println("1. Bank Administrator");
        System.out.println("2. Customer");

        // Get the user type
        int userType = scanner.nextInt();
        int operation;
        switch (userType) {
            case 1 -> {
                // Bank Administrator
                System.out.println("Hello Bank Administrator!");
                System.out.println("Select the operation to perform:");
                System.out.println("1. View all Customers data");
                System.out.println("2. View all issued cards");
                System.out.println("3. Add new Customer");
                System.out.println("4. Issue new credit card");
                System.out.println("5. View blocked cards");
                System.out.println("6. Close/block credit card");
                System.out.println("7. Logout");

                // Get the operation
                operation = scanner.nextInt();

                // Switch on the operation
                switch (operation) {
                    // Todo implement this functionality
                }
            }
            case 2 -> {
                // Customer
                System.out.println("Hello Customer!");
                System.out.println("Select the operation to perform:");
                System.out.println("1. Apply for new credit card");
                System.out.println("2. View Balance");
                System.out.println("3. Close/block credit card");
                System.out.println("4. Logout");

                // Get the operation
                operation = scanner.nextInt();

                // Switch on the operation
                switch (operation) {
                    //Todo Need to implement this functionality
                }
            }
            default -> System.out.println("Invalid user type.");
        }


        
    }
}
