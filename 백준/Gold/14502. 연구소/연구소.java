import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 1. N, M입력
 * 2. 배열 입력
 * 3. 백트래킹으로 벽 세울 곳 정하고, 3개 다 세웠다면 바이러스 퍼뜨리고 안전구역 개수 구하기
 *
 */
public class Main {
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static final int WALL_SIZE = 3;
	static int n, m;
	static int maxSafetySize = Integer.MIN_VALUE;
	static int[][] arr;
	static ArrayList<Point> virusList;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. N, M 입력
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//2. 배열 입력
		virusList = new ArrayList<>();
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2) {
					virusList.add(new Point(i, j));
				}
			}
		}
		
		//3. 백트래킹으로 벽 세울 곳 정하고, 3개 다 세웠다면 안전구역 개수 구하기
		makeWall(0);
		
		System.out.println(maxSafetySize);
	}
	
	public static void makeWall(int wall) {
		if(wall == WALL_SIZE) {
			spreadVirus();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				//안전구역이면 벽 세워보고 넘어가기
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					makeWall(wall + 1);
					arr[i][j] = 0;
				}
			}
		}
	}
	
	public static void spreadVirus() {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		
		int[][] tempArr = new int[n][m];
	    
		for (int i = 0; i < n; i++) {
			tempArr[i] = arr[i].clone();
	    }
	      
		
		for(Point point : virusList) {
			queue.add(point);
			visited[point.x][point.y] = true;
		}
			
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				//범위 밖이면
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				//벽이면 못감
				if(tempArr[nx][ny] == 1) continue;
				
				if(visited[nx][ny]) continue;
				
				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
				tempArr[nx][ny] = 2;
			}
		}
		
		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tempArr[i][j] == 0)
					count++;
			}
		}
		
		maxSafetySize = Math.max(count, maxSafetySize);
	}
}