import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int n, m;
    static int[] coins;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            coins = new int[n + 1];
            dp = new int[n + 1][10001];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
                dp[i][coins[i]]++;
            }
            m = Integer.parseInt(br.readLine());

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if(j - coins[i] < 0) {
                        dp[i][j] = dp[i - 1][j];
                        continue;
                    }
                    dp[i][j] += dp[i - 1][j] + dp[i][j - coins[i]];
                }
            }
            System.out.println(dp[n][m]);
        }
    }
}
