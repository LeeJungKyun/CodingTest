import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[n + 1][21];

        dp[1][arr[1]] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 20; j++) {
                if(dp[i - 1][j] != 0){
                    int plus = j + arr[i];
                    int minus = j - arr[i];
                    if(isValid(plus))
                        dp[i][plus] += dp[i-1][j];
                    if(isValid(minus))
                        dp[i][minus] += dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n - 1][arr[n]]);
    }

    public static boolean isValid(int num) {
        return (num >= 0 && num <= 20);
    }
}
