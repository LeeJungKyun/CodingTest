
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		long[] dp = new long[101];
		
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		
		for(int i = 4; i <= 100; i++) {
			dp[i] = dp[i-3] + dp[i-2];
		}
	
		int testCase = Integer.parseInt(br.readLine());
		
		for(int i = 0;  i < testCase; i++) {
			int n = Integer.parseInt(br.readLine());
			
			sb.append(dp[n]).append("\n");
		}
		
		System.out.println(sb);
	}
}
