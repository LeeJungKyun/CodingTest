import java.io.*;
import java.util.*;

public class Main {
    static int c, n;
    static int[] dp;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            //0 : 비용, 1 : 증가 고객
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //필요한 비용만큼 dp 배열 생성
        dp = new int[c + 100];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        //비용마다 탐색할 루프
        for (int i = 0; i < n; i++) {
            int cost = arr[i][0];
            int customer = arr[i][1];

            for (int j = customer; j < dp.length; j++) {
                if (dp[j - customer] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - customer] + cost);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = c; i < dp.length; i++) {
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);
    }
}
