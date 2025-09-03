import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 이용권의 종류는 4개
 * 12달이니깐 DP[13] ? 
 * 
 * 3달 이용권 골랐으면 다음 3달은 그 비용으로 채우고
 * 1년짜리 이용권은 그냥 배열에 미리 다 채워놓기
 */

public class Solution {
	static final int DAY = 0, MONTH = 1, THREE_MONTH = 2, YEAR = 3;
	static int testCase;
	static int[] memberShip, plan;
	static int minCost;
	static int[] dp;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//변수 초기화
			minCost = Integer.MIN_VALUE;
			
			//이용권 4개 입력
			memberShip = new int[4];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++)
				memberShip[i] = Integer.parseInt(st.nextToken());
			
			//12개월 이용계획 입력 (편하게 1-based로 ㄱㄱ)
			plan = new int[13];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= 12; i++)
				plan[i] = Integer.parseInt(st.nextToken());
			
			//=== DP배열 선언해서 계산 시작 ===
			
			dp = new int[13];
			dp[0] = 0;
			
			//2월부터 DP채우기
			for (int month = 1; month <= 12; month++) {
			    // 1일권 사용
			    dp[month] = dp[month - 1] + plan[month] * memberShip[DAY];

			    // 1달권 사용
			    dp[month] = Math.min(dp[month], dp[month - 1] + memberShip[MONTH]);

			    // 3달권 사용
			    if (month >= 3)
			        dp[month] = Math.min(dp[month], dp[month - 3] + memberShip[THREE_MONTH]);
			    else
			        dp[month] = Math.min(dp[month], memberShip[THREE_MONTH]);
			}
			minCost = Math.min(dp[12], memberShip[YEAR]);
			sb.append('#').append(tc).append(' ').append(minCost).append('\n');
		}
		System.out.println(sb);
	}
}