import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int[][][] dp;
	static int n;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new int[n + 1][n + 1];
		// 0: 가로, 1: 세로, 2: 대각선
		dp = new int[n + 1][n + 1][3];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[1][2][0] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (arr[i][j] == 1) {
					continue;
				}

				// 가로방향 = 가로방향 y - 1 값 + 대각선에서 온 값
				dp[i][j][0] += (dp[i][j - 1][0] + dp[i][j - 1][2]);

				// 세로방향
				dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];

				// 대각선
				if (i >= 2 && j >= 2 && arr[i - 1][j] == 0 && arr[i][j - 1] == 0) {
					dp[i][j][2] += (dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]);
				}
			}
		}

		int result = dp[n][n][0] + dp[n][n][1] + dp[n][n][2];

		System.out.println(result);

	}
}