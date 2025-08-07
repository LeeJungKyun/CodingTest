/**
 * @author 이정균
 * 문제 : https://www.acmicpc.net/problem/15650
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 수열은 오름차순이어야 한다.
 * 1. 한 줄에 들어오는 N과 M을 입력받는다.
 * 2. elementArray 배열을 N크기로 초기화한다.
 * 3. selectedArray를 M크기로 선언한다.
 * 4. combination 재귀 함수 실행
 * 	4-1. combination 함수의 파라미터인 selectedIndex가 m이면 출력
 * 	4-2. 그렇지 않다면 오름차순으로 하기 위해서 들어오는 startIndex 파라미터부터 n까지 반복문
 * 	4-3. 반복문 내에서 결과 배열의 selectedIndex번째에 elementArray 의 startIndex 값 삽입
 * 	4-4. elementIndex + 1과 startIndex + 1을 인자로 넘겨주고 재귀함수
 */

import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] elementArray, selectedArray;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		1. 한 줄에 들어오는 N과 M을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
//		2. elementArray와 visited 배열을 N크기로 초기화한다.
		elementArray = new int[n];
		for(int i = 0; i < n; i++)
			elementArray[i] = i + 1;
//		3. selectedArray를 M크기로 선언한다.
		selectedArray = new int[m];
//		4. combination 재귀 함수 실행
		combination(0, 0);
	}
	
	static void combination(int startIndex, int selectedIndex) {
//		4-1. combination 함수의 파라미터인 selectedIndex가 m이면 출력
		if(selectedIndex == m) {
			for(int selectedNum : selectedArray)
				System.out.print(selectedNum + " ");
			System.out.println();
			return;
		}
		
//		4-2. 그렇지 않다면 오름차순으로 하기 위해서 들어오는 startIndex 파라미터부터 n까지 반복문
		for(int elementIndex = startIndex; elementIndex < n; elementIndex++) {
//			4-3. 반복문 내에서 결과 배열의 selectedIndex번째에 elementArray 의 startIndex 값 삽입
			selectedArray[selectedIndex] = elementArray[elementIndex];
//			4-4. elementIndex + 1과 startIndex + 1을 인자로 넘겨주고 재귀함수
			combination(elementIndex + 1, selectedIndex + 1);
		}
	}
}
