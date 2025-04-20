import java.util.*;
import java.io.*;

public class Main {

  static int n, m;
  static int[][] arr;
  static int[][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[n][m];
    dp = new int[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int sum = 0;

    for (int i = 0; i < m; i++) {
      sum += arr[0][i];
      dp[0][i] = sum;
    }

    sum = 0;
    for (int i = 0; i < n; i++) {
      sum += arr[i][0];
      dp[i][0] = sum;
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1])) + arr[i][j];
      }
    }

    System.out.print(dp[n - 1][m - 1]);
  }
}
