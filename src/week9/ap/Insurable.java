import java.util.Scanner;

interface Insurable {
    double calculateInsurance(double declaredValue);
}

abstract class Parcel {
    protected String type;
    protected double weightKg;
    protected double declaredValue;

    public Parcel(String type, double weightKg, double declaredValue) {
        this.type = type;
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    public abstract double calculateShippingCharge();

    public double calculateInsurance() {
        if (this instanceof Insurable) {
            return ((Insurable) this).calculateInsurance(declaredValue);
        }
        return 0.0;
    }

    public double calculateTotal() {
        return calculateShippingCharge() + calculateInsurance();
    }

    public void printDetails() {
        System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f\n",
                type, calculateShippingCharge(), calculateInsurance(), calculateTotal());
    }
}

class StandardParcel extends Parcel {
    public StandardParcel(double weightKg, double declaredValue) {
        super("STANDARD", weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        return 40.0 + (10.0 * weightKg);
    }
}

class ExpressParcel extends Parcel implements Insurable {
    public ExpressParcel(double weightKg, double declaredValue) {
        super("EXPRESS", weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        return 80.0 + (15.0 * weightKg);
    }

    @Override
    public double calculateInsurance(double value) {
        return 0.02 * value;
    }
}

class FragileParcel extends Parcel implements Insurable {
    public FragileParcel(double weightKg, double declaredValue) {
        super("FRAGILE", weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        StandardParcel tempStandard = new StandardParcel(weightKg, declaredValue);
        return tempStandard.calculateShippingCharge() + 50.0;
    }

    @Override
    public double calculateInsurance(double value) {
        return 0.02 * value;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        Parcel[] parcels = new Parcel[n];
        double grandTotal = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();

            if (type.equalsIgnoreCase("STANDARD")) {
                parcels[i] = new StandardParcel(weight, value);
            } else if (type.equalsIgnoreCase("EXPRESS")) {
                parcels[i] = new ExpressParcel(weight, value);
            } else if (type.equalsIgnoreCase("FRAGILE")) {
                parcels[i] = new FragileParcel(weight, value);
            }
        }

        for (Parcel p : parcels) {
            p.printDetails();
            grandTotal += p.calculateTotal();
        }

        System.out.printf("Grand Total: %.2f\n", grandTotal);
        sc.close();
    }
}