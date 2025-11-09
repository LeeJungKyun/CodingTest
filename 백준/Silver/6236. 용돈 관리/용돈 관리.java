import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] arr;
    static int max = 0;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];

        for (int i = 0; i < N; ++i) {
            arr[i] = sc.nextInt();
            max = Integer.max(max, arr[i]);
        }
        int left = max;
        int right = 10_000 * 100_000;
        int count = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (M >= getWithdrawalCount(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }
    static int getWithdrawalCount(int withdrawalAmount) {
        int count = 1;
        int money = withdrawalAmount;

        for (int i : arr) {
            money -= i;
            if (money < 0) {
                ++count;
                money = withdrawalAmount - i;
            }
        }
        return count;
    }
}