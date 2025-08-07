/**
 * @author 이정균
 * 
 * N x N 크기의 표가 주어지고 M개의 x1, y1, x2, y2가 주어질때 (x1, y1)부터 (x2, y2)의 합을 구하는 프로그램
 *
 * 1. N, M 입력받기
 * 2. N x N 표 입력 받기
 * 3. 누적합 배열 선언 후 계산
 * 4. M개의 줄에서 좌표 입력받기
 * 5. 좌표 기반 누적합 계산
 */

import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] elementArr, cumulativeSumArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. N, M 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//2. N x N 표 입력받기
		elementArr = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				elementArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//3. 누적합 배열 선언 후 계산
		cumulativeSumArr = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				cumulativeSumArr[i][j] = elementArr[i - 1][j - 1] + cumulativeSumArr[i - 1][j] + cumulativeSumArr[i][j - 1]
						- cumulativeSumArr[i - 1][j - 1];
			}
		}
		
		//4. M개의 줄에서 좌표 입력받기
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			//5. 좌표기반 누적합 계산
			int calculatedSum = cumulativeSumArr[x2][y2] - cumulativeSumArr[x1 - 1][y2] - cumulativeSumArr[x2][y1 - 1] + cumulativeSumArr[x1 - 1][y1 - 1];
			System.out.println(calculatedSum);
		}
	}
}
