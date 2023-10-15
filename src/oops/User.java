package oops;

import java.util.Scanner;

abstract class User {
    protected Scanner scanner;

    public User(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void performOperations();
}
