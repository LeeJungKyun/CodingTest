import java.io.*;
import java.util.*;

/**
 * @author 이정균 DP배열을 만드는데 [1 << n][n] 크기로 선언
 * 일단 dp 초기값은 거리로 저장해놓기
 * visited 1 ~ 1 << n 까지 돌면서 확인 
 *
 */
public class Solution {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static final int MAX = Integer.MAX_VALUE;
	static int testCase;
	static int n;
	static int minDistance;
	static Point[] customerArr;
	static Point home, company;
	static int[][] dp;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			customerArr = new Point[n];
			for (int i = 0; i < n; i++) {
				customerArr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// DP 배열 초기화
			dp = new int[1 << n][n];
			for (int[] row : dp) {
				Arrays.fill(row, MAX);
			}

			// DP 초기값 설정: 회사 -> 각 고객으로 가는 거리
			for (int i = 0; i < n; i++) {
				dp[1 << i][i] = calculateDistance(company, customerArr[i]);
			}

			// DP 계산
			for (int visited = 1; visited < (1 << n); visited++) {
				for (int i = 0; i < n; i++) {
					// 현재 위치 i를 방문한 경우
					if ((visited & (1 << i)) != 0) {
						// dp[visited][i]가 유효한 값인 경우에만 진행
						if (dp[visited][i] == MAX)
							continue;

						// 다음 방문할 고객 j를 탐색
						for (int j = 0; j < n; j++) {
							// j를 아직 방문하지 않은 경우만 확인
							if ((visited & (1 << j)) != 0)
								continue;
							int newVisited = visited | (1 << j);
							int newDistance = dp[visited][i] + calculateDistance(customerArr[i], customerArr[j]);

							// 최소 거리 갱신
							if (newDistance < dp[newVisited][j]) {
								dp[newVisited][j] = newDistance;
							}
						}
					}
				}
			}

			// 모든 고객을 방문한 상태에서 집으로 돌아가는 최소 거리 계산
			minDistance = MAX;
			int allVisitMask = (1 << n) - 1;
			for (int i = 0; i < n; i++) {
				if (dp[allVisitMask][i] != MAX) {
					minDistance = Math.min(minDistance, dp[allVisitMask][i] + calculateDistance(customerArr[i], home));
				}
			}

			sb.append('#').append(tc).append(' ').append(minDistance).append('\n');
		}

		System.out.println(sb);
	}

	// 맨해튼 거리 계산 메서드
	public static int calculateDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}