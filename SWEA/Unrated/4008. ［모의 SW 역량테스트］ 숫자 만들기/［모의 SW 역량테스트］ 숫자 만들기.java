/**
 * @author 이정균
 * 테케 주어지고
 * 숫자 개수 N이 주어지고
 * 각 연산자의 개수가 +, -, *, / 로 주어짐
 * 수식에 사용되는 숫자가 주어짐
 * 연산자의 우선순위는 무시하고 왼쪽에서 오른쪽으로 차례대로 계산
 * 나올 수 있는 최댓값과 최솟값의 차이
 *
 * 1. testCase 입력
 * 2. 각 테케별 N 입력
 * 3. 연산자 배열 입력 (+, -, *, /)
 * 4. 피연산자 배열 입력 -> 크기 N
 * 5. 연산자의 순열을 구해서 계산을 실행
 */

import java.io.*;
import java.util.*;

public class Solution {
	static int testCase, n, max, min;
	static int[] operator, operand, selectedOperand;
	static boolean[] isSelected;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. testCase 입력
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 각 테케별 N 입력
			n = Integer.parseInt(br.readLine());
			//3. 연산자 배열 입력 (+, -, *, /)
			operator = new int[4];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++)
				operator[i] = Integer.parseInt(st.nextToken());
			
			//4. 피연산자 배열 입력 -> 크기 N
			operand = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				operand[i] = Integer.parseInt(st.nextToken());
			
			
			//5. 연산자의 순열을 구해서 계산을 실행
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			getPermutation(1, operand[0]);
			
			sb.append("#").append(tc).append(" ").append(max - min).append('\n');
		}
		System.out.println(sb);
	}
	public static void getPermutation(int depth, int result) {
		//모든 연산자 다 사용
		if(depth == n) {
			max = Math.max(result, max);
			min = Math.min(result, min);
		}
		
		for(int i = 0; i < 4; i++) {
			if(operator[i] > 0) {
				operator[i]--;
				int nextResult = calculate(result, operand[depth], i);
				getPermutation(depth + 1, nextResult);
				operator[i]++;
			}
		}
	}
	
	public static int calculate(int x, int y, int operator){
		//+, -, *, /
		switch(operator) {
		case 0:
			return x + y;
		case 1:
			return x - y;
		case 2:
			return x * y;
		case 3:
			return x / y;
		}
		return 0;
	}
}
