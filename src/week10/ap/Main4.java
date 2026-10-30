import java.util.Scanner;
import java.util.Arrays;

public class Main4 {

    public static String[] rotateRoster(String[] names, long k) {
        int n = names.length;
        if (n == 0) return names;

        int effectiveK = (int) (k % n);
        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            int newIndex = (i + effectiveK) % n;
            result[newIndex] = names[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
        }

        long k = sc.nextLong();

        String[] rotated = rotateRoster(names, k);
        System.out.println(Arrays.toString(rotated));

        sc.close();
    }
}