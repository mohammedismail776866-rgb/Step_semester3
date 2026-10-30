import java.util.Scanner;

interface NightServiceable {
    default double getNightMultiplier() {
        return 1.20;
    }
}

abstract class Cab {
    protected String cabType;
    protected double km;
    protected String time;

    public Cab(String cabType, double km, String time) {
        this.cabType = cabType;
        this.km = km;
        this.time = time;
    }

    public abstract double getRatePerKm();

    public boolean isTripValid() {
        if (time.equalsIgnoreCase("NIGHT") && !(this instanceof NightServiceable)) {
            return false;
        }
        return true;
    }

    public double calculateFare() {
        double rawFare = km * getRatePerKm();
        if (time.equalsIgnoreCase("NIGHT") && this instanceof NightServiceable) {
            rawFare *= ((NightServiceable) this).getNightMultiplier();
        }
        return Math.max(rawFare, 100.0);
    }

    public void processTrip(double[] totalContainer) {
        if (!isTripValid()) {
            System.out.printf("%s: night service not available\n", cabType);
        } else {
            double fare = calculateFare();
            System.out.printf("%s: %.2f\n", cabType, fare);
            totalContainer[0] += fare;
        }
    }
}

class MiniCab extends Cab {
    public MiniCab(double km, String time) {
        super("MINI", km, time);
    }

    @Override
    public double getRatePerKm() {
        return 10.0;
    }
}

class SedanCab extends Cab implements NightServiceable {
    public SedanCab(double km, String time) {
        super("SEDAN", km, time);
    }

    @Override
    public double getRatePerKm() {
        return 14.0;
    }
}

class SUVCab extends Cab implements NightServiceable {
    public SUVCab(double km, String time) {
        super("SUV", km, time);
    }

    @Override
    public double getRatePerKm() {
        return 18.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        Cab[] trips = new Cab[n];
        double[] total = new double[1];

        for (int i = 0; i < n; i++) {
            String cabType = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();

            if (cabType.equalsIgnoreCase("MINI")) {
                trips[i] = new MiniCab(km, time);
            } else if (cabType.equalsIgnoreCase("SEDAN")) {
                trips[i] = new SedanCab(km, time);
            } else if (cabType.equalsIgnoreCase("SUV")) {
                trips[i] = new SUVCab(km, time);
            }
        }

        for (Cab cab : trips) {
            cab.processTrip(total);
        }

        System.out.printf("Total: %.2f\n", total[0]);
        sc.close();
    }
}