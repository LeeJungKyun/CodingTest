import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] dp;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
        
        if(n % 2 == 1) {
			System.out.println(0);
			return;
		}
		
		dp = new int[n + 1];
		
		dp[0] = 1;
		dp[2] = 3;
		
		//홀수는 만들 수 없음
		for(int i = 4; i <= n; i += 2) {
			//기본으로 나올 수 있는 모양
			// i - 2 칸을 dp[2] 처럼 채우고, 특수모양 2가지
			dp[i] += dp[i - 2] * dp[2] + 2;
			
			for(int j = i - 2; j >= 4; j -= 2) {
				dp[i] += dp[i - j] * 2;
			}
		}
		
		System.out.println(dp[n]);
	}
}