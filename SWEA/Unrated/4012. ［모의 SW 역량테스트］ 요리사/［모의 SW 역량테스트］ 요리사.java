/**
 * @author 이정균
 * N개의 식재료를 N / 2 개씩 나누어서 두 개의 요리 -> 각 음식이 A, B
 * A음식과 B음식의 맛의 차이가 최소가 되도록 재료를 배분
 * 식재료 i, 식재료 j -> 시너지 Sij 가 발생
 * 각 음식의 맛은 시너지 Sij들의 합
 * 
 * 백트래킹
 * 
 * 1. testCase입력
 * 2. testCase별 필요 변수 초기화
 * 3. n입력
 * 4. synergy배열 입력
 * 5. isSelected 배열 초기화
 * 6. selectFood 함수 실행(현재 선택된 인덱스, 개수)
 * 	6-1. 개수가 n / 2개면 반띵한거니깐 계산
 * 	6-2. 선택된 인덱스부터 n까지 반복하면서 선택되지 않았다면 조합 확인
 */

import java.io.*;
import java.util.*;

public class Solution {
	static int testCase, n;
	static int minDiff;
	static int[][] synergy;
	static boolean[] isSelected;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. testCase입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. testCase별 필요 변수 초기화
			minDiff = Integer.MAX_VALUE;
			//3. n입력
			n = Integer.parseInt(br.readLine());
			
			//4. synergy배열 입력
			synergy = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//5. isSelected 배열 초기화
			isSelected = new boolean[n];
			//6. selectFood 함수 실행(현재 선택된 인덱스, 개수)
			selectFood(0, 0);
			sb.append('#').append(tc).append(' ').append(minDiff).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void selectFood(int selectedIdx, int count) {
		//6-1. 개수가 n / 2개면 반띵한거니깐 계산
		if(count == n / 2) {
			calculateDifference();
			return;
		}
		
		//6-2. 선택된 인덱스부터 n까지 반복하면서 선택되지 않았다면 조합 확인
		for (int i = selectedIdx; i < n; i++) {
            if (!isSelected[i]) {
            	isSelected[i] = true;
            	selectFood(i + 1, count + 1);
                isSelected[i] = false;
            }
        }
	}

	public static void calculateDifference() {
		List<Integer> foodA = new ArrayList<>();
        List<Integer> foodB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (isSelected[i]) {
                foodA.add(i);
            } else {
                foodB.add(i);
            }
        }

        int tasteA = getSynergy(foodA);
        int tasteB = getSynergy(foodB);
        minDiff = Math.min(minDiff, Math.abs(tasteA - tasteB));
	}
	
	static int getSynergy(List<Integer> list) {
        int sum = 0;
        int size = list.size();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int a = list.get(i);
                int b = list.get(j);
                sum += synergy[a][b] + synergy[b][a];
            }
        }

        return sum;
    }
}
