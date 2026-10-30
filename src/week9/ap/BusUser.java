import java.util.Scanner;

interface BusUser {
    default double getTransportFee() {
        return 12000.0;
    }
}

abstract class Student {
    protected String name;

    public Student(String name) {
        this.name = name;
    }

    public abstract double calculateTuition();

    public double calculateTotalFee() {
        double fee = calculateTuition();
        if (this instanceof BusUser) {
            fee += ((BusUser) this).getTransportFee();
        }
        return fee;
    }

    public void printFee() {
        System.out.printf("%s: %.2f\n", name, calculateTotalFee());
    }
}

class DayScholar extends Student implements BusUser {
    public DayScholar(String name) {
        super(name);
    }

    @Override
    public double calculateTuition() {
        return 40000.0;
    }
}

class Hosteller extends Student {
    public Hosteller(String name) {
        super(name);
    }

    @Override
    public double calculateTuition() {
        return 40000.0 + 60000.0;
    }
}

class Scholar extends Student implements BusUser {
    public Scholar(String name) {
        super(name);
    }

    @Override
    public double calculateTuition() {
        return 20000.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        Student[] students = new Student[n];
        double totalCollected = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();

            if (type.equalsIgnoreCase("DAY_SCHOLAR")) {
                students[i] = new DayScholar(name);
            } else if (type.equalsIgnoreCase("HOSTELLER")) {
                students[i] = new Hosteller(name);
            } else if (type.equalsIgnoreCase("SCHOLAR")) {
                students[i] = new Scholar(name);
            }
        }

        for (Student student : students) {
            student.printFee();
            totalCollected += student.calculateTotalFee();
        }

        System.out.printf("Total Collected: %.2f\n", totalCollected);
        sc.close();
    }
}