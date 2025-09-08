import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 계단수 : 인접한 모든 자리의 차이가 1인 수
 * 
 * DP 배열은 3차원
 * DP[i][j][k]
 * 
 * i자리 숫자, j로 끝나는 숫자, 사용한 숫자 k
 */
public class Main {
	static final int MOD = 1_000_000_000;
	
	static int n;
	static long[][][] dp;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//숫자의 길이가 n -> 그러면 가능한 수의 개수는 10^n개
		n = Integer.parseInt(br.readLine());
		
		dp = new long[n + 1][11][1 << 10];
		
		//DP초기화
		//일단 한자리 수일때를 초기화 하면 될 듯
		//식의 의미 : 1자리 수일때, i로 끝나는 숫자는 1 << i 를 사용해서 만든 숫자임
		for(int i = 1; i < 10; i++) {
			dp[1][i][1 << i] = 1;
		}
		
		//자리수 digit
		for(int digit = 2; digit <= n; digit++) {
			//현재 자릿수의 마지막에 오는 숫자는 0부터 9까지 가능
			for(int lastNum = 0; lastNum < 10; lastNum++) {
				//사용된 숫자들을 확인할 usedNum 변수
				for(int usedNum = 0; usedNum < (1 << 10); usedNum++){
					//마지막으로 온 숫자확인
					int afterUsingLastNum = usedNum | (1 << lastNum);
					
					//마지막에 올 숫자가 9면 8만 가능
					if(lastNum == 9) {
						dp[digit][lastNum][afterUsingLastNum] = (dp[digit][lastNum][afterUsingLastNum]
								+ dp[digit - 1][lastNum - 1][usedNum]) % MOD;
					}
					//마지막에 오는 숫자가 0이면 1만 가능
					else if(lastNum == 0) {
						dp[digit][lastNum][afterUsingLastNum] = (dp[digit][lastNum][afterUsingLastNum]
								+ dp[digit - 1][lastNum + 1][usedNum]) % MOD;
					}
					//나머지는 양쪽 숫자
					else {
						dp[digit][lastNum][afterUsingLastNum] = (dp[digit][lastNum][afterUsingLastNum]
								+ dp[digit - 1][lastNum + 1][usedNum]
								+ dp[digit - 1][lastNum - 1][usedNum]
										) % MOD;
					}
				}
			}
		}
		
		long sum = 0;
		for(int i = 0; i < 10; i++)
			sum = (sum + dp[n][i][(1 << 10) - 1]) % MOD;
		System.out.println(sum);
	}
}