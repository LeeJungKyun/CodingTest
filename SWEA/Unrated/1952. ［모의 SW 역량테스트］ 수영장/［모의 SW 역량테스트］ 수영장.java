/**
 * @author 이정균
 * 1년동안 각 달의 이용 계획을 수립하고 가장 적은 비용으로 수영장을 이용
 * 1일 이용권, 1달 이용권, 3달 이용권(11월 ~ 1월, 12월 ~ 2월 구매 불가), 1년 이용권 4종류
 * 각 달의 이동계획은 12칸 짜리 배열로 주어짐
 * 
 * 백트래킹 -> 각 달을 서로 다른 이용권으로 이용했을 때를 가정해서 재귀함수 돌리면 될 것 같음
 * 
 * 1. testCase 입력
 * 2. 각 테케별 필요한 변수들 초기화
 * 3. 이용권 별 요금 입력
 * 4. 이용 계획 입력
 * 5. findMinCost 함수 실행 (월, 플랜, 비용)
 * 	5-1. 현재까지 계산된 비용이 minCost보다 크면 바로 종료
 * 	5-2. 종료조건 month가 13이면 계산하고 minCost 갱신
 * 	5-3. 이번달에 이용 계획이 없으면 계산 안하고 넘어감
 *  5-4. 그렇지 않으면 1일권 사용시 계산, 1달권 사용시 계산, 3달권 이용시 month + 3 해서 계산(11월 미만때만)
 */

import java.io.*;
import java.util.*;

public class Solution {
	static int testCase, minCost;
	static int[] cost, plan;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. testCase 입력
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 각 테케별 필요한 변수들 초기화
			cost = new int[4];
			plan = new int[13];
			
			//3. 이용권 별 요금 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++)
				cost[i] = Integer.parseInt(st.nextToken());
			minCost = cost[3];
			//4. 이용 계획 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= 12; i++)
				plan[i] = Integer.parseInt(st.nextToken());
			
			//5. findMinCost 실행 (월, 비용)
			findMinCost(1, 0);
			
				
			
			sb.append('#').append(tc).append(' ').append(minCost).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void findMinCost(int month,int curCost) {
		//5-1. 현재까지 계산된 비용이 minCost보다 크면 바로 종료
		if(curCost > minCost) return;
		//5-2. 종료조건 month가 13이면 계산하고 minCost 갱신
		if(month > 12) {
			minCost = Math.min(curCost, minCost);
			return;
		}
		
		//5-3. 이번달에 이용 계획이 없으면 계산 안하고 넘어감
		if(plan[month] == 0) 
			findMinCost(month + 1, curCost);
		else {
			//5-4. 그렇지 않으면 1일권 사용시 계산, 1달권 사용시 계산, 3달권 이용시 month + 3 해서 계산(11월 미만때만)
			findMinCost(month + 1, curCost + plan[month] * cost[0]);
			findMinCost(month + 1, curCost + cost[1]);
			findMinCost(month + 3, curCost + cost[2]);
		}
		
		
	}
}
