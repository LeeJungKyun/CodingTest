import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int s = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int distance = Math.abs(arr[i] - s);
            int cost = 0;

            if (distance > 2 * k) {
                cost += (distance - 2 * k) * l;
            } else if (distance <= 2 * k) {
                if (distance % 2 == 0) {
                    cost += (k - distance / 2) * 2;
                } else if (distance % 2 == 1) {
                    cost += (k - distance / 2) * 2 - 1;
                }
            }
            dp[i] = cost;
        }

        int min = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (min > dp[i]) {
                min = dp[i];
                idx = i + 1;
            }
        }

        System.out.println(min + " " + idx);
    }
}