import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 1_000_000_007;
	static int n;
	static int[] dp;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());

		if(n == 1) {
			System.out.println(0);
			return;
		}
		
		long[][] dp = new long[3][n + 1];
		dp[0][2] = dp[1][2] = 1;

		for (int i = 3; i <= n; i++) {
			dp[0][i] = (dp[1][i - 1] + dp[2][i - 1]) % MOD;
			dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD;
			dp[2][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD;
		}

		System.out.println(dp[0][n]);
	}
}