import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr;
    static long[][] dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if(dp[x][y] == 0 || arr[x][y] == 0)
                    continue;

                int jump = arr[x][y];
                int down = x + jump;
                int right = y + jump;

                if(down < n)
                    dp[down][y] += dp[x][y];

                if(right < n)
                    dp[x][right] += dp[x][y];
            }
        }

        System.out.println(dp[n - 1][n - 1]);
    }
}
