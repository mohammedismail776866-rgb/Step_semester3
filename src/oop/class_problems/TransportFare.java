
import java.util.*;

abstract class Transport {
    double distance;

    Transport(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();

    abstract String getType();
}

class Bus extends Transport {
    Bus(double d) {
        super(d);
    }

    double calculateFare() {
        return Math.min(2 + 0.10 * distance, 10);
    }

    String getType() {
        return "BUS";
    }
}

class Train extends Transport {
    Train(double d) {
        super(d);
    }

    double calculateFare() {
        return 3 + 0.15 * distance;
    }

    String getType() {
        return "TRAIN";
    }
}

class Metro extends Transport {
    double peakFactor;

    Metro(double d, double p) {
        super(d);
        peakFactor = p;
    }

    double calculateFare() {
        return (1.50 + 0.20 * distance) * peakFactor;
    }

    String getType() {
        return "METRO";
    }
}

public class TransportFare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double distance = sc.nextDouble();
            Transport t;

            switch (type) {
                case "BUS":
                    t = new Bus(distance);
                    break;
                case "TRAIN":
                    t = new Train(distance);
                    break;
                default:
                    double factor = sc.nextDouble();
                    t = new Metro(distance, factor);
            }

            double fare = t.calculateFare();
            System.out.printf("%s: %.2f%n",
                    t.getType(), fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
