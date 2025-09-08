import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 1. 테스트 케이스 입력
 * 2. 동전의 가지 수 N
 * 3. 동전의 금액이 오름차순으로 주어짐
 * 4. 목표금액 입력
 * 5. DP배열은 목표금액 + 1 크기
 * 	5-1. i 로 동전 고르고, j는 현재 탐색할 금액을 고르기
 *
 */
public class Main {
	static int testCase, coinCount, target;
	static int[] coin, dp;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 동전의 가지 수 N
			coinCount = Integer.parseInt(br.readLine());
			
			//3. 동전의 금액 오름차순
			coin = new int[coinCount];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < coinCount; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			//4. 목표금액 입력
			target = Integer.parseInt(br.readLine());
			
			//5. DP배열은 목표금액 + 1 크기
			dp = new int[target + 1];
			
			
			//5-1. i 로 동전 고르고, j는 현재 탐색할 금액을 고르기
			for (int i = 0; i < coinCount; i++) {
                for (int j = 1; j <= target; j++) {
                	// 현재 탐색중인 금액에서 선택한 동전의 금액을 뺀게 0보다 크면 dp[j - coin[i]] 더해주기
                    if (j - coin[i] > 0) {
                        dp[j] += dp[j - coin[i]];
                    }
                    // 탐색 중인 금액이 동전의 가치와 같다면 그냥 그거로 만드는거임
                    else if (j - coin[i] == 0) {
                        dp[j]++;
                    }
                }
            }
			
			sb.append(dp[target]).append('\n');
		}
		System.out.println(sb);
	}
}