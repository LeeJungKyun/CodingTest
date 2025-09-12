import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N x N 홈방범 서비스
 * 홈방범 서비스는 중심으로부터 맨해튼 거리만큼 제공
 * 
 * 손해를 보지 않는 한 최대한 많은 집에 홈방범 서비스
 * 
 * 운영비용은 서비스영역의 크기 K라고 할 때, K^2 - (K - 1)^2
 * arr를 벗어나도 운영비용은 변하지 않음
 * 
 * 5 <= n <= 20
 * 1 <= m <= 10
 * 
 * 이득 = m * 범위 내의 집 - 운영비용
 * 
 * 50개에 3초
 * 완탐으로 하면 터질거같은데 ( 각 셀마다 0 ~ 20 )
 * 1. 도시의 크기 n, 지불할 비용 m 입력
 * 2. 집 배열 입력
 */

public class Solution {
	//클래스 선언부
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int n, m;
	static int[][] arr;
	static int maxHomeCount;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; tc++) {
			maxHomeCount = MIN;
			
			//1. n, m 입력
			st = new StringTokenizer(br.readLine().trim());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			//2. 집 입력
			arr = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int row = 0; row < n; row++) {
				for(int col = 0; col < n; col++) {
					//서비스 범위를 고르기 (1 ~ n)
					for(int range = 1; range <= 2 * n; range++) {
						int cost = calculateServiceCost(range);
						int homeCount = searchHome(row, col, range);
						
						int benefit = homeCount * m - cost;
						
						if(benefit >= 0) {
							//System.out.printf("%d, %d 에서 시작해서 %d 만큼 커버하면 집 개수가 %d고, 드는 비용은 %d, 집에서 내는 비용이 %d 라서 %d 벌었음\n", row, col, range, homeCount, cost, homeCount * m, benefit);
							maxHomeCount = Math.max(maxHomeCount, homeCount);
						}
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(maxHomeCount).append('\n');
		}
		System.out.println(sb);
	}
	
	//x, y부터 맨해튼거리로 k만큼 탐색했을 때 집이 몇개 나 있는지
	public static int searchHome(int x, int y, int k) {
	    int count = 0;
	    int range = k - 1;

	    for (int dx = -k + 1; dx <= k - 1; dx++) {
	        int dyRange = k - 1 - Math.abs(dx);
	        for (int dy = -dyRange; dy <= dyRange; dy++) {
	            int nx = x + dx;
	            int ny = y + dy;

	            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
	                if (arr[nx][ny] == 1) {
	                    count++;
	                }
	            }
	        }
	    }
	    return count;
	}

	
	public static int calculateServiceCost(int k) {
	    return k * k + (k - 1) * (k - 1);
	}
}