import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * * N명의 고객
 * 회사, 집, 집의 좌표는 이차원 정수 좌표
 * 거리는 맨해튼 거리
 * * 회사 출발 -> N명의 고객을 모두 방문 -> 집으로 돌아오는 최소 경로
 * * 1. 테케 입력
 * 2. N입력
 * 3. 한줄 입력받아서 첫 좌표 회사, 두번째는 집, 나머지 손님
 * 4. 재귀함수를 이용해 모든 순열을 탐색
 */

public class Solution {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int testCase;
	static int n;
	static int minDistance; // 최소 거리를 저장할 변수
	static Point home, company;
	static Point[] customerArr; // 고객 좌표를 배열로 저장
	static boolean[] visited; // 고객 방문 여부 체크
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테케 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. N입력
			n = Integer.parseInt(br.readLine());
			
			//3. 한줄 입력받아서 첫 좌표 회사, 두번째는 집, 나머지 손님
			st = new StringTokenizer(br.readLine());
			
			//회사
			company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			//집
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			//나머지 손님
			customerArr = new Point[n];
			for(int i = 0; i < n; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				customerArr[i] = new Point(x, y);
			}
			
			// 변수 초기화
			minDistance = Integer.MAX_VALUE;
			visited = new boolean[n];
			
			// 4. 재귀함수를 이용해 모든 순열을 탐색
			// 시작점은 회사, 현재까지 거리는 0, 방문한 고객 수 0
			dfs(company, 0, 0);
			
			//출력
			sb.append('#').append(tc).append(' ').append(minDistance).append('\n');
		}
		
		System.out.println(sb);
	}
	
	// DFS를 이용한 순열 탐색 함수
	public static void dfs(Point currentPoint, int currentDistance, int visitedCount) {
		// 현재 거리가 이미 최소 거리보다 크거나 같으면 더 이상 탐색할 필요 없음
		if (currentDistance >= minDistance) {
			return;
		}
		
		// 모든 고객을 방문했다면
		if (visitedCount == n) {
			// 마지막 고객에서 집까지의 거리 추가
			currentDistance += calculateDistance(currentPoint, home);
			
			// 최소 거리 갱신
			minDistance = Math.min(minDistance, currentDistance);
			return;
		}
		
		// 다음 방문할 고객을 선택
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				Point nextPoint = customerArr[i];
				int newDistance = currentDistance + calculateDistance(currentPoint, nextPoint);
				
				dfs(nextPoint, newDistance, visitedCount + 1);
				
				visited[i] = false; // 백트래킹
			}
		}
	}
	
	// 맨해튼 거리 계산 메서드
	public static int calculateDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}
