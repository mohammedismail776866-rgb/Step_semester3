
import java.util.*;

abstract class Room {
    int units;

    Room(int units) {
        this.units = units;
    }

    abstract double calculateBill();
    abstract String getType();
}

class SingleRoom extends Room {
    SingleRoom(int units) {
        super(units);
    }

    double calculateBill() {
        return units * 8.0;
    }

    String getType() {
        return "SINGLE";
    }
}

class SharedRoom extends Room {
    int occupants;

    SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    double calculateBill() {
        return units * 6.0 / occupants;
    }

    String getType() {
        return "SHARED";
    }
}

class ACRoom extends Room {
    ACRoom(int units) {
        super(units);
    }

    double calculateBill() {
        return units * 10.0 + 200;
    }

    String getType() {
        return "AC";
    }
}

public class HostelElectricity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            int units = sc.nextInt();
            Room r;

            switch (type) {
                case "SINGLE":
                    r = new SingleRoom(units);
                    break;
                case "SHARED":
                    int occupants = sc.nextInt();
                    r = new SharedRoom(units, occupants);
                    break;
                default:
                    r = new ACRoom(units);
            }

            double bill = r.calculateBill();
            System.out.printf("%s: %.2f%n",
                    r.getType(), bill);
            total += bill;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
