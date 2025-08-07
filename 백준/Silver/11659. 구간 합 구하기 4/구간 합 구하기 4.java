/**
 * @author 이정균
 * 수 N개가 주어졌을 때, i번째 수부터 j번째 수까지의 합을 구하는 프로그램
 * 
 * 1. N과 M 입력
 * 2. N개의 수를 입력
 * 3. 누적합 배열 계산
 * 4. M줄에 구간 i, j 입력
 *
 */
import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] elementArr, cumulativeSum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//1. N과 M 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//2. N개의 수를 입력
		elementArr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			elementArr[i] = Integer.parseInt(st.nextToken());
		}
		
		//3. 누적합 배열 계산
		cumulativeSum = new int[n + 1];
		cumulativeSum[0] = 0;
		for(int i = 1; i <= n; i++) {
			cumulativeSum[i] = elementArr[i - 1] + cumulativeSum[i - 1];
		}
		
		//4. M줄에 구간 i, j 입력
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startIndex = Integer.parseInt(st.nextToken());
			int endIndex = Integer.parseInt(st.nextToken());
			
			System.out.println(cumulativeSum[endIndex] - cumulativeSum[startIndex - 1]);
		}
	}
}
