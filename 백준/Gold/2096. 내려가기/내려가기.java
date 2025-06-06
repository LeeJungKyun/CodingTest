import java.io.*;
import java.util.*;
public class Main {
    static int[][] arr;
    static int[][] minDp;
    static int[][] maxDp;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1][3];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minDp = new int[n + 1][3];
        maxDp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            maxDp[i][0] += Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + arr[i][0];
            maxDp[i][1] += Math.max(Math.max(maxDp[i - 1][0], maxDp[i - 1][1]), maxDp[i - 1][2]) + arr[i][1];
            maxDp[i][2] += Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + arr[i][2];

            minDp[i][0] += Math.min(minDp[i - 1][0], minDp[i - 1][1]) + arr[i][0];
            minDp[i][1] += Math.min(Math.min(minDp[i - 1][0], minDp[i - 1][1]), minDp[i - 1][2]) + arr[i][1];
            minDp[i][2] += Math.min(minDp[i - 1][1], minDp[i - 1][2]) + arr[i][2];
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minDp[n][i]);
            max = Math.max(max, maxDp[n][i]);
        }

        System.out.println(max + " " + min);
    }
}
