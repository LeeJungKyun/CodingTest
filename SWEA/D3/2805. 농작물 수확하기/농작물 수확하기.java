/**
 * @author 이정균
 * 그냥 for문 별찍기처럼 구현
 * 
 * 1. testCase 입력
 * 2. 각 testCase별 필요 변수 초기화
 * 3. N입력받기
 * 4. farm배열 초기화 후 입력
 * 5. 계산 : 각 row별 start = abs (n / 2 - row), end = n = start
 * 6. 출력
 */

import java.io.*;
import java.util.*;

public class Solution {
	static int testCase, n;
	static int sum;
	static int[][] farm;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. testCase 입력
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 각 testCase별 필요 변수 초기화
			sum = 0;
			//3. N입력받기
			n = Integer.parseInt(br.readLine());
			
			//4. farm배열 초기화 후 입력
			farm = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				String line = br.readLine();
				for(int j = 0; j < n; j++) {
					farm[i][j] = line.charAt(j) - '0';
				}
			}
			
			//5. 계산 : 각 row별 start = abs (n / 2 - row), end = n = start
			for (int row = 0; row < n; row++) {
				int start = Math.abs(n / 2 - row);
				int end = n - start;
				for (int col = start; col < end; col++) {
					sum += farm[row][col];
				}
			}
			//6. 출력
			sb.append('#').append(tc).append(' ').append(sum).append('\n');
		}
		System.out.println(sb);
	}
}
