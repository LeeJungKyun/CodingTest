import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] cost, dp;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		//비용 입력 RGB
		cost = new int[n][3];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int color = 0; color < 3; color++) {
				cost[i][color] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		//비용 계산
		dp = new int[n][3];
		for(int i = 0; i < 3; i++)
			dp[0][i] = cost[0][i];
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < 3; j++) {
				//R로 칠할때
				if(j == 0) {
					dp[i][j] = cost[i][j] +  Math.min(dp[i - 1][1], dp[i - 1][2]);
				}
				//G로 칠할때
				else if(j == 1) {
					dp[i][j] = cost[i][j] +  Math.min(dp[i - 1][0], dp[i - 1][2]);
				}
				//B로 칠할때
				else {
					dp[i][j] = cost[i][j] +  Math.min(dp[i - 1][0], dp[i - 1][1]);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			min = Math.min(dp[n - 1][i], min);
		}
		
		System.out.println(min);
	}
}