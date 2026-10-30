import java.util.Scanner;

public class Main5 {

    private static int findFirstGreaterOrEqual(int[] scores, int target) {
        int low = 0;
        int high = scores.length - 1;
        int ans = scores.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private static int findFirstStrictlyGreater(int[] scores, int target) {
        int low = 0;
        int high = scores.length - 1;
        int ans = scores.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int countInBand(int[] scores, int low, int high) {
        int leftIndex = findFirstGreaterOrEqual(scores, low);
        int rightIndex = findFirstStrictlyGreater(scores, high);
        return rightIndex - leftIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        int low = sc.nextInt();
        int high = sc.nextInt();

        System.out.println(countInBand(scores, low, high));
        sc.close();
    }
}