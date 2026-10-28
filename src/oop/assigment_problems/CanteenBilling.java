
import java.util.*;

abstract class Customer {
    double amount;

    Customer(double amount) {
        this.amount = amount;
    }

    abstract double calculateAmount();
    abstract String getType();
}

class Student extends Customer {
    Student(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount * 0.90;
    }

    String getType() {
        return "STUDENT";
    }
}

class Staff extends Customer {
    Staff(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount * 0.95;
    }

    String getType() {
        return "STAFF";
    }
}

class Guest extends Customer {
    Guest(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount + 10;
    }

    String getType() {
        return "GUEST";
    }
}

public class CanteenBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double amount = sc.nextDouble();
            Customer c;

            switch (type) {
                case "STUDENT":
                    c = new Student(amount);
                    break;
                case "STAFF":
                    c = new Staff(amount);
                    break;
                default:
                    c = new Guest(amount);
            }

            double result = c.calculateAmount();
            System.out.printf("%s: %.2f%n",
                    c.getType(), result);
            total += result;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
