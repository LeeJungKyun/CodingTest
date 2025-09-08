import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 * 그냥 냅색
 */

public class Solution {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int itemCount, capacity;
	static int[] weight, value;
	static int[] dp;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//물건 개수, 가방의 부피 입력
			st = new StringTokenizer(br.readLine());
			itemCount = Integer.parseInt(st.nextToken());
			capacity = Integer.parseInt(st.nextToken());
			
			//물건의 부피, 가치 입력
			weight = new int[itemCount];
			value = new int[itemCount];
			
			for(int i = 0; i < itemCount; i++) {
				st = new StringTokenizer(br.readLine());

				weight[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
			}
			
			//DP테이블 선언
			dp = new int[capacity + 1];
			dp[0] = 0;
			for(int i = 0; i < itemCount; i++) {
				for(int j = capacity; j >= weight[i]; j--) {
					dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
				}
			}
			
			sb.append('#').append(tc).append(' ').append(dp[capacity]).append('\n');
		}
		System.out.println(sb);
	}
}