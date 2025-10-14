import java.io.*;
import java.util.*;

public class Main {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	static final int MOD = 10007;
	//변수 선언부
	static int n, m, h;
	static List<Integer>[] block;
	static int[][] dp;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n, m, h입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		//2. 각 학생들이 가진 블록 입력
		block = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			block[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				block[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//0-1 냅색같은데
		//3. dp 배열 선언
		dp = new int[n + 1][h + 1];
		dp[0][0] = 1;
		
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= h; j++) {
				dp[i][j] = dp[i - 1][j];
			}
			
			for(int blockHeight : block[i]) {
				for(int j = blockHeight; j <= h; j++) {
					dp[i][j] += dp[i - 1][j - blockHeight];
					dp[i][j] %= MOD;
				}
			}
		}
		
		System.out.println(dp[n][h]);
	}
}