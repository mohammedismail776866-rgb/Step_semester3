
import java.util.*;

abstract class Delivery {
    double weight, distance;

    Delivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    abstract double calculateFee();

    abstract String getType();
}

class StandardDelivery extends Delivery {
    StandardDelivery(double w, double d) {
        super(w, d);
    }

    double calculateFee() {
        return 5 + 0.50 * weight + 0.10 * distance;
    }

    String getType() {
        return "STANDARD";
    }
}

class ExpressDelivery extends Delivery {
    ExpressDelivery(double w, double d) {
        super(w, d);
    }

    double calculateFee() {
        return 15 + weight + 0.20 * distance;
    }

    String getType() {
        return "EXPRESS";
    }
}

class InternationalDelivery extends Delivery {
    double customsFee;

    InternationalDelivery(double w, double d, double c) {
        super(w, d);
        customsFee = c;
    }

    double calculateFee() {
        return 25 + 2 * weight + 0.50 * distance
                + customsFee;
    }

    String getType() {
        return "INTERNATIONAL";
    }
}

public class DeliveryFee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double weight = sc.nextDouble();
            double distance = sc.nextDouble();
            Delivery d;

            switch (type) {
                case "STANDARD":
                    d = new StandardDelivery(weight, distance);
                    break;
                case "EXPRESS":
                    d = new ExpressDelivery(weight, distance);
                    break;
                default:
                    double customs = sc.nextDouble();
                    d = new InternationalDelivery(
                            weight, distance, customs);
            }

            double fee = d.calculateFee();
            System.out.printf("%s: %.2f%n",
                    d.getType(), fee);
            total += fee;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}

