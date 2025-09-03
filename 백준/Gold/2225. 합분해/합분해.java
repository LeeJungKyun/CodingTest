import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1_000_000_000;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int n, k;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//n까지의 숫자중 k개로 n을 만들거니깐
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[n + 200][n + 200];
		
		//1로 i를 만들 수 있는 경우는 하나임
		for(int i = 0; i <= n; i++)
			dp[1][i] = 1;
		
		for(int i = 1; i <= k; i++) {
			for(int j = 0; j <= n; j++) {
				for(int k = 0; k <= j; k++) {
					dp[i][j] += dp[i - 1][j - k];
					dp[i][j] %= MOD;
				}
			}
		}
		System.out.println(dp[k][n]);
	}
}