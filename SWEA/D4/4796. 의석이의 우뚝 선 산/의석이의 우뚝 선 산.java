/**
 * @author 이정균
 * 동서방향 산 N개 -> h배열
 * 높이가 같은 지점은 없음
 * 조건
 * i <= l < k 인 모든 l에 대해 hl < h(l+1) 이 성립
 * k <= l < j 인 모든 l에 대해 hl > h(l+1) 이 성립
 * 증가하는 수열을 찾음 [a, b] -> [a, b], [a + 1, b], ... , [b - 1, b] 가능
 * 감소하는 수열을 찾음 [c, d] -> [c, c + 1], [c, c + 2], ... , [c, d] 가능
 * b와 c가 같다면 증가가 가능한 수 X 감소가 가능한 수 (b - a) x (d - c)
 * 
 * 1. testCase 입력
 * 2. 각 테스트케이스 별 필요 변수 초기화
 * 3. 산 개수 n과 나무 높이 height 배열 입력
 * 4. 증가하는 구간 구하기 left[i]
 * 5. 감소하는 구간 구하기 right[i]
 * 6. 꼭대기점에서 가능한 (left × right) 계산
 */

import java.io.*;
import java.util.*;

public class Solution {
	static int testCase, n, count;
	static int[] height;
	static ArrayList<int[]> increasingList, decreasingList;
	static Scanner sc;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		//1. testCase 입력
		testCase = sc.nextInt();
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 각 테스트케이스 별 필요 변수 초기화
			count = 0;
			
			//3. 산 개수 n과 나무 높이 height 배열 입력
			n = sc.nextInt();
			height = new int[n];
			for(int i = 0; i < n; i++)
				height[i] = sc.nextInt();
			
			
			// 4. 왼쪽에서 오른쪽으로 증가 구간 길이 저장
            int[] left = new int[n];
            for (int i = 1; i < n; i++) {
                if (height[i] > height[i - 1]) {
                    left[i] = left[i - 1] + 1;
                }
            }

            // 5. 오른쪽에서 왼쪽으로 감소 구간 길이 저장
            int[] right = new int[n];
            for (int i = n - 2; i >= 0; i--) {
                if (height[i] > height[i + 1]) {
                    right[i] = right[i + 1] + 1;
                }
            }

            // 6. 꼭대기점에서 가능한 (left × right) 계산
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (left[i] > 0 && right[i] > 0) {
                    count += left[i] * right[i];
                }
            }
			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
}
