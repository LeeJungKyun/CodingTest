import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 * 땅이 N x N 크기 -> 여기에 등산로 지을래
 * 등산로는 가장 높은 봉우리에서 시작하고, 높은 지형에서 낮은 지형으로 연결 (높이 가 같은 곳 혹은 낮은 지형, 대각선 연결 불가)
 * 최대한 긴 등산로를 만들기 위해서 최대 K만큼 깎을 수 있는 기회를 딱 한번 제공
 * 만들 수 있는 가장 긴 등산로는?
 * 
 * 브레인스토밍
 * 일단 가장 높은 지점을 찾아서 해당지점부터 다 탐색한다는 가정(최대 5개)
 * N x N 모든 지점을 1 ~ K 만큼 깎아서 다 탐색하는 경우의 수 1600개
 * 이 경우마다 그래프 탐색해봤자 얼마 안걸릴거같긴한데 ㄱㄱ
 * 
 * 변수 범위
 * 3 <= N <= 8
 * 1 <= K <= 5
 * 높이는 1 이상 20 이하의 정수
 * 지형을 깎아서 0으로 만들어도 됩니당
 * 시간 제한
 * 3초
 * 
 * 1. 각 테스트케이스 별로 N, K 입력
 * 2. 지도 정보 입력하면서 최대 높이 입력
 * 3. 가장 긴 등산로 찾는 함수 시작
 * 
 * findLongestPath
 * 현재 방식에서는 봉우리가 깎일 수도 있기 때문에 이 함수 내에서 봉우리 리스트 저장
 * 1. arr를 돌면서 봉우리들을 리스트에 저장
 */

public class Solution {
	//클래스 선언부
	static class Point {
		int x, y, length;

		public Point(int x, int y, int length) {
			this.x = x;
			this.y = y;
			this.length = length;
		}
	}
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	
	//변수 선언부
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int testCase;
	static int n, k;
	static int[][] arr;
	static int maxHeight, maxLength;
	static ArrayList<Point> summits;
	
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			maxLength = MIN;
			//1. 각 테스트케이스 별로 N, K 입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			//2. 지도 정보 입력
			arr = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxHeight = findMaxHeight();
			summits = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(arr[i][j] == maxHeight)
						summits.add(new Point(i, j, 1));
				}
			}
			
			//모든 지점의 높이를 다 깎아가면서 확인.,,,
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					for(int minus = 1; minus <= k; minus++) {
						arr[i][j] -= minus;
						findLongestPath();
						arr[i][j] += minus;
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(maxLength).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void findLongestPath() {
		//정상 봉우리에 대해서 BFS돌면서 최장 찾기
		for(Point p : summits) {
			ArrayDeque<Point> queue = new ArrayDeque<>();
			boolean[][] visited = new boolean[n][n];
			queue.add(p);
			visited[p.x][p.y] = true;
			
			while(!queue.isEmpty()) {
				Point cur = queue.poll();
				
				maxLength = Math.max(cur.length, maxLength);
				
				for(int dir = 0; dir < 4; dir++) {
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];
					
					//탈출 방지
					if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					
					//방문했으면 도망
					if(visited[nx][ny]) continue;
					
					//내 위치이상이면 도망
					if(arr[nx][ny] >= arr[cur.x][cur.y]) continue;
					queue.add(new Point(nx, ny, cur.length + 1));
				}
			}
		}
	}
	
	//최대 높이 리턴
	public static int findMaxHeight() {
		int max = MIN;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}
		
		return max;
	}
}