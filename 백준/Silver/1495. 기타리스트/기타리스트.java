import java.io.*;
import java.util.*;

public class Main {
    static int n, s, m;
    static int[] arr;
    static int[][] dp;
    static int result = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //곡 별 볼륨 저장
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n + 1][m + 1];

        dp[0][s] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                // i-1 번째 곡의 j라는 볼륨을 연주 가능
                if (dp[i][j] == 1) {
                    int plus = j + arr[i];
                    if (isValid(plus)) {
                        dp[i + 1][plus] = 1;
                    }

                    int minus = j - arr[i];
                    if (isValid(minus)) {
                        dp[i + 1][minus] = 1;
                    }
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            if (dp[n][i] == 1) {
                result = Math.max(result, i);
            }
        }

        System.out.println(result);
    }

    public static boolean isValid(int num) {
        return (0 <= num && num <= m);
    }
}
