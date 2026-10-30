import java.util.Scanner;

abstract class Ticket {
    protected int count;
    protected static final double CONVENIENCE_FEE = 20.0;

    public Ticket(int count) {
        this.count = count;
    }

    public abstract String getSeatType();
    public abstract double getPricePerTicket();

    public double calculateAmount() {
        return count * (getPricePerTicket() + CONVENIENCE_FEE);
    }

    public void printBooking() {
        System.out.printf("%s: %.2f\n", getSeatType(), calculateAmount());
    }
}

class RegularTicket extends Ticket {
    public RegularTicket(int count) {
        super(count);
    }

    @Override
    public String getSeatType() {
        return "REGULAR";
    }

    @Override
    public double getPricePerTicket() {
        return 150.0;
    }
}

class PremiumTicket extends Ticket {
    public PremiumTicket(int count) {
        super(count);
    }

    @Override
    public String getSeatType() {
        return "PREMIUM";
    }

    @Override
    public double getPricePerTicket() {
        return 250.0;
    }
}

class ReclinerTicket extends Ticket {
    public ReclinerTicket(int count) {
        super(count);
    }

    @Override
    public String getSeatType() {
        return "RECLINER";
    }

    @Override
    public double getPricePerTicket() {
        return 400.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        Ticket[] bookings = new Ticket[n];
        double totalCollected = 0;

        for (int i = 0; i < n; i++) {
            String seat = sc.next();
            int count = sc.nextInt();

            if (seat.equalsIgnoreCase("REGULAR")) {
                bookings[i] = new RegularTicket(count);
            } else if (seat.equalsIgnoreCase("PREMIUM")) {
                bookings[i] = new PremiumTicket(count);
            } else if (seat.equalsIgnoreCase("RECLINER")) {
                bookings[i] = new ReclinerTicket(count);
            }
        }

        for (Ticket ticket : bookings) {
            ticket.printBooking();
            totalCollected += ticket.calculateAmount();
        }

        System.out.printf("Total: %.2f\n", totalCollected);
        sc.close();
    }
}