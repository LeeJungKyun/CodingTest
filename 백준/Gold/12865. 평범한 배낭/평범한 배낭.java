import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int w, v;
    static int[] W;
    static int[] V;

    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][k + 1];

        W = new int[n];
        V = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (W[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i - 1]] + V[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        System.out.println(dp[n][k]);

    }
}
