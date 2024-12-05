import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static long[][] dp;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1][n + 1];
        dp = new long[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = arr[1][1];
        res = dp[1][1];

        // DP 계산
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
            }
        }

        // 마지막 줄에서 최대값 계산
        for (int j = 1; j <= n; j++) {
            res = Math.max(res, dp[n][j]);
        }

        System.out.println(res);
    }
}