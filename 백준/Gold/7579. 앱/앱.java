import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] memory, cost;
    static int[][] dp;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        memory = new int[n];
        cost = new int[n];
        dp = new int[n][10001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int curCost = cost[i];
            int curMemory = memory[i];
            for (int j = 0; j <= 10000; j++) {
                if (i == 0) {
                    if (j >= curCost)
                        dp[i][j] = curMemory;
                } else {
                    if (j >= curCost) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curCost] + curMemory);
                    } else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= m)
                    result = Math.min(result, j);
            }
        }
        System.out.println(result);
    }
}
