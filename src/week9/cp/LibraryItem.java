import java.util.Scanner;

abstract class LibraryItem {
    protected String title;
    protected int daysLate;

    public LibraryItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    public abstract double calculateFine();

    public void printFine() {
        System.out.printf("%s: %.2f\n", title, calculateFine());
    }
}

class Book extends LibraryItem {
    public Book(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 2.0;
    }
}

class DVD extends LibraryItem {
    public DVD(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return Math.min(daysLate * 5.0, 50.0);
    }
}

class Magazine extends LibraryItem {
    public Magazine(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 1.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LibraryItem[] items = new LibraryItem[n];
        double totalFines = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String title = sc.next();
            int days = sc.nextInt();

            if (type.equalsIgnoreCase("BOOK")) {
                items[i] = new Book(title, days);
            } else if (type.equalsIgnoreCase("DVD")) {
                items[i] = new DVD(title, days);
            } else if (type.equalsIgnoreCase("MAGAZINE")) {
                items[i] = new Magazine(title, days);
            }
        }

        for (LibraryItem item : items) {
            item.printFine();
            totalFines += item.calculateFine();
        }

        System.out.printf("Total Fines: %.2f\n", totalFines);
        sc.close();
    }
}