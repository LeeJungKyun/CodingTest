import java.util.Scanner;

/**
 * 1. 나누기 3
 * 2. 나누기 2
 * 3. 1 빼기
 */
public class Main {
    static int n;
    static int[] dp;
    static int[] trace;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp = new int[n + 2];
        trace = new int[n + 2];

        StringBuilder sb = new StringBuilder();

        dp[1] = 0;
        trace[1] = -1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            trace[i] = i - 1;

            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                trace[i] = i / 2;
            }

            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                trace[i] = i / 3;
            }
        }

        int num = dp[n];

        sb.append(num).append('\n');

        int index = n;

        for (int i = 0; i <= num; i++) {
            sb.append(index).append(" ");
            index = trace[index];
        }

        System.out.println(sb);

        sc.close();
    }
}
