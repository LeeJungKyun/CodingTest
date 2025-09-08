import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 1. n, k 입력
 * 2. k 줄에 중요도, 공부시간 입력
 * 3. DP테이블 채우기
 */
public class Main {
	static int n, k;
	static int[] important, studyTime, dp;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		important = new int[k];
		studyTime = new int[k];
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int importance = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			important[i] = importance;
			studyTime[i] = time;
		}
		
		dp = new int[n + 1];
		dp[0] = 0;
		
		for(int i = 0; i < k; i++) {
			for(int j = n; j >= studyTime[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - studyTime[i]] + important[i]);
			}
		}
		
		int result = 0;
		for(int num : dp)
			result = Math.max(result, num);
		System.out.println(result);
	}
}