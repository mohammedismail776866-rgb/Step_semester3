import java.util.Scanner;

abstract class Staff {
    protected String name;

    public Staff(String name) {
        this.name = name;
    }

    public abstract double calculatePay();

    public void printPay() {
        System.out.printf("%s: %.2f\n", name, calculatePay());
    }
}

class FullTimeStaff extends Staff {
    private double weeklySalary;

    public FullTimeStaff(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double calculatePay() {
        return weeklySalary;
    }
}

class HourlyStaff extends Staff {
    private double hours;
    private double rate;

    public HourlyStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculatePay() {
        if (hours <= 40) {
            return hours * rate;
        } else {
            return (40 * rate) + ((hours - 40) * 1.5 * rate);
        }
    }
}

class InternStaff extends Staff {
    private double stipend;

    public InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    public double calculatePay() {
        return stipend;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Staff[] staffList = new Staff[n];
        double totalPayroll = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            if (type.equalsIgnoreCase("FULLTIME")) {
                double salary = sc.nextDouble();
                staffList[i] = new FullTimeStaff(name, salary);
            } else if (type.equalsIgnoreCase("HOURLY")) {
                double hours = sc.nextDouble();
                double rate = sc.nextDouble();
                staffList[i] = new HourlyStaff(name, hours, rate);
            } else if (type.equalsIgnoreCase("INTERN")) {
                double stipend = sc.nextDouble();
                staffList[i] = new InternStaff(name, stipend);
            }
        }

        for (Staff s : staffList) {
            s.printPay();
            totalPayroll += s.calculatePay();
        }

        System.out.printf("Total Payroll: %.2f\n", totalPayroll);
        sc.close();
    }
}