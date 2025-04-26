import java.util.*;
import java.io.*;

public class Main {

  static int n, m;
  static int[][] arr;
  static int[][][] dp;
  static int min = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[n][m];
    //좌대각선 0, 가운데 1, 우대각선 2
    dp = new int[n][m][3];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
        Arrays.fill(dp[i][j], Integer.MAX_VALUE);
      }
    }

    //DP 첫줄 초기화
    for (int i = 0; i < m; i++) {
      dp[0][i][0] = arr[0][i];
      dp[0][i][1] = arr[0][i];
      dp[0][i][2] = arr[0][i];
    }

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < m; j++) {
        //맨 앞
        if (j == 0) {
          //전 방향이 직선,
          dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + arr[i][j];
          dp[i][j][1] = dp[i - 1][j][2] + arr[i][j];
        }
        //맨 뒤
        else if (j == m - 1) {
          dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + arr[i][j];
          dp[i][j][1] = dp[i - 1][j][0] + arr[i][j];
        }
        //나머지
        else {
          dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + arr[i][j];
          dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + arr[i][j];
          dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + arr[i][j];
        }
      }
    }
    
    for (int col = 0; col < m; col++) {
      for (int idx = 0; idx < 3; idx++) {
        min = Math.min(dp[n - 1][col][idx], min);
      }
    }
    
    System.out.println(min);
  }
}