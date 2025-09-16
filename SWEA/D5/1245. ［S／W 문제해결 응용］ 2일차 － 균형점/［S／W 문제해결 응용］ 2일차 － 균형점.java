import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N개의 자성체 좌표(x, y, z)가 주어지는데 y, z 좌표는 동일하고 x좌표만 달라서 일직선상에 주어짐
 * 인력 = 자성체와 물체 사이의 거리 (d)와 자성체와 물체의 질량(m)
 * F = G * m1 * m2 / (d * d)
 */
public class Solution {
	
	// 변수 선언부
	static int testCase;
	static int n;
	static int[] pointArray;
	static int[] massArray;
	static ArrayList<Double> balancePointList;
	
	// 입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {

			// 1. 자성체의 개수 N 입력
			n = Integer.parseInt(br.readLine());
			
			// 2. 좌표와 질량 입력받기
			pointArray = new int[n];
			massArray = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int pointIndex = 0; pointIndex < n; pointIndex++) {
				pointArray[pointIndex] = Integer.parseInt(st.nextToken());
			}
			
			for(int massIndex = 0; massIndex < n; massIndex++) {
				massArray[massIndex] = Integer.parseInt(st.nextToken());
			}
			
			// 3. 균형점 구하기 (N개의 자성체 -> N - 1개의 균형점)
			balancePointList = new ArrayList<>();
			
			// 각 인접한 자성체 쌍 사이에 존재하는 균형점을 찾기 위해 반복
			for(int i = 0; i < n - 1; i++) {
				balancePointList.add(findBalancePoint(i, i + 1));
			}
			
			// 4. 결과 출력
			sb.append('#').append(tc).append(' ');
			
			for(double balancePoint : balancePointList)
				sb.append(String.format("%.10f", balancePoint)).append(' ');
			
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 이분 탐색으로 두 자성체 사이에 존재하는 균형점찾기
	 * 균형점은 모든 자성체로부터 받는 힘의 합이 0이 되는 지점
	 *
	 * @param left  현재 탐색 구간의 왼쪽 자성체
	 * @param right 현재 탐색 구간의 오른쪽 자성체
	 * @return 찾은 균형점 좌표
	 */
	public static double findBalancePoint(int left, int right) {
		// 이분 탐색의 시작점과 끝점
		double low = pointArray[left];
		double high = pointArray[right];
		double mid = 0.0;
	
		// 오차가 1e-12보다 작아질 때까지 반복
		while (high - low > 1e-12) {
			mid = (low + high) / 2.0;
			double totalForce = 0.0;
	
			// 모든 자성체로부터 mid 지점에 작용하는 총 힘 계산
			for (int k = 0; k < n; k++) {
				double distance = mid - pointArray[k];
	
				// 자성체 바로 위에 위치하는 경우 (거리 0), 계산을 건너뜀
				if (Math.abs(distance) < 1e-12) continue;
	
				double force = (double) massArray[k] / (distance * distance);
				
				// 물체(mid)가 자성체(k)의 오른쪽에 있을 때
				// 자성체는 물체를 왼쪽으로 끌어당김
				if (distance > 0) {
					totalForce -= force;
				} 
				// 물체(mid)가 자성체(k)의 왼쪽에 있을 때
				// 자성체는 물체를 오른쪽으로 끌어당김
				else {
					totalForce += force;
				}
			}
	
			// 총 힘의 방향에 따라 다음 탐색 범위를 결정
			if (totalForce > 0) {
				// 총 힘이 양수(+)면 물체는 오른쪽으로 밀리고 있음
				// 균형점은 더 왼쪽에 있으므로, 탐색 범위를 mid의 왼쪽으로
				high = mid;
			} else {
				// 총 힘이 음수(-)면 물체는 왼쪽으로 끌려가고 있음
				// 균형점은 더 오른쪽에 있으므로, 탐색 범위를 mid의 오른쪽으로
				low = mid;
			}
		}
		return (low + high) / 2.0;
	}
}