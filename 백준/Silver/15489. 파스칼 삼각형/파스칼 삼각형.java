import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 1
 * 1 1
 * 1 2  1
 * 1 3  3  1
 * 1 4  6  4 1
 * 1 5 10 10 5  1
 * 1 6 15 20 15 6 1
 * 
 *
 */
public class Main {
	static int[][] dp;
	static int r, c, w;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		int size = r + w;
		dp = new int[size + 1][size + 1];
		
		dp[1][1] = dp[2][1] = dp[2][2] = 1;
		
		for(int i = 3; i <= size; i++) {
			dp[i][1] = 1;
		}
		
		for(int i = 3; i <= size; i++) {
			for(int j = 1; j <= i; j++) {
				if(j == 1) continue;
				if(j == i) {
					dp[i][j] = 1;
				}
				else {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
				}
			}
		}
		
		int sum = 0;
		
		for (int i = 0; i < w; i++) {
            for (int j = 0; j <= i; j++) {
                sum += dp[r + i][c + j];
            }
        }
		
		System.out.println(sum);
	}
}