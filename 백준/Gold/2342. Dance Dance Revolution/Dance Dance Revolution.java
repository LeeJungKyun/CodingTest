import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][][] dp;
    static Point left, right;
    static int[] arr;
    static int n;
    static final int INF = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[100001];
        int idx = 0;
        while (true) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) break;
            arr[idx++] = num;
        }
        n = idx;

        /**
         * dp[step][left][right] 현재 step 까지 왼발이 left, 오른발이 right 일 때 최소 에너지 소비량
          */
        dp = new int[n + 1][5][5];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[0][0][0] = 0;

        for (int step = 0; step < n; step++) {
            int next = arr[step];
            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    int cur = dp[step][left][right];
                    if(cur == INF) continue;

                    dp[step + 1][next][right] = Math.min(dp[step + 1][next][right], cur + calculate(left, next));
                    dp[step + 1][left][next] = Math.min(dp[step + 1][left][next], cur + calculate(right, next));
                }
            }
        }

        int result = INF;
        for (int left = 0; left < 5; left++) {
            for (int right = 0; right < 5; right++) {
                result = Math.min(result, dp[n][left][right]);
            }
        }

        System.out.println(result);

    }

    static int calculate(int from, int to) {
        int distance = Math.abs(from - to);
        if (from == 0)
            return 2;
        else if (distance == 0) {
            return 1;
        } else if (distance == 1 || distance == 3)
            return 3;
        else return 4;
    }
}
