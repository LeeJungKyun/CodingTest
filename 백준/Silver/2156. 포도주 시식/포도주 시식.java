import java.util.Scanner;

public class Main {
    static int n;
    static int[] arr;
    static int[][] dp;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        arr = new int[n + 1];
        dp = new int[3][n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        max = arr[1];

        dp[0][1] = 0;
        dp[1][1] = arr[1];
        dp[2][1] = 0;

        if (n >= 2) {
            dp[0][2] = dp[1][1];
            dp[1][2] = arr[2];
            dp[2][2] = dp[1][1] + arr[2];
            max = dp[2][2];
        }


        for (int i = 3; i <= n; i++) {
            dp[0][i] = Math.max(dp[1][i - 1], dp[2][i - 1]);
            dp[1][i] = Math.max(dp[2][i - 2], Math.max(dp[1][i - 2], dp[0][i - 2])) + arr[i];
            dp[2][i] = dp[1][i - 1] + arr[i];
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        System.out.println(max);
    }
}

