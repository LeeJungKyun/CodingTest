import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] arr;
    static int[][] dp;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            arr[i][0] = row;
            arr[i][1] = col;
        }

        /**
         * N * N 크기의 DP 배열
         * DP[i][j] 값은 i번째 부터 j번째 행렬을 곱하는 최소 곱셈 연산 수를 저장
         */
        dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                //i번째를 기준으로 len 만큼 갔을때의 총 길이 j
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i][0] * arr[k][1] * arr[j][1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        System.out.println(dp[0][n - 1]);
        br.close();
    }
}
