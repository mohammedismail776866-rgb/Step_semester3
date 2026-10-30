import java.util.Scanner;

abstract class Plot {
    protected String owner;

    public Plot(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public abstract String getShapeName();
    public abstract double calculateArea();

    public void printReport() {
        System.out.printf("%s (%s): %.2f\n", owner, getShapeName(), calculateArea());
    }
}

class CirclePlot extends Plot {
    private double radius;

    public CirclePlot(String owner, double radius) {
        super(owner);
        this.radius = radius;
    }

    @Override
    public String getShapeName() {
        return "CIRCLE";
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class RectanglePlot extends Plot {
    private double length;
    private double width;

    public RectanglePlot(String owner, double length, double width) {
        super(owner);
        this.length = length;
        this.width = width;
    }

    @Override
    public String getShapeName() {
        return "RECTANGLE";
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}

class TrianglePlot extends Plot {
    private double base;
    private double height;

    public TrianglePlot(String owner, double base, double height) {
        super(owner);
        this.base = base;
        this.height = height;
    }

    @Override
    public String getShapeName() {
        return "TRIANGLE";
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Plot[] plots = new Plot[n];
        double totalArea = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String owner = sc.next();
            if (type.equalsIgnoreCase("CIRCLE")) {
                double radius = sc.nextDouble();
                plots[i] = new CirclePlot(owner, radius);
            } else if (type.equalsIgnoreCase("RECTANGLE")) {
                double length = sc.nextDouble();
                double width = sc.nextDouble();
                plots[i] = new RectanglePlot(owner, length, width);
            } else if (type.equalsIgnoreCase("TRIANGLE")) {
                double base = sc.nextDouble();
                double height = sc.nextDouble();
                plots[i] = new TrianglePlot(owner, base, height);
            }
        }

        for (Plot plot : plots) {
            plot.printReport();
            totalArea += plot.calculateArea();
        }

        System.out.printf("Total Area: %.2f\n", totalArea);
        sc.close();
    }
}