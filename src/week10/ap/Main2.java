import java.util.Scanner;

public class Main2 {

    public static void attendanceSummary(int[] days) {
        int presentCount = 0;
        int maxStreak = 0;
        int currentStreak = 0;

        for (int day : days) {
            if (day == 1) {
                presentCount++;
                currentStreak++;
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                }
            } else {
                currentStreak = 0;
            }
        }

        System.out.printf("Present: %d, Longest streak: %d\n", presentCount, maxStreak);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[i] = sc.nextInt();
        }

        attendanceSummary(days);
        sc.close();
    }
}