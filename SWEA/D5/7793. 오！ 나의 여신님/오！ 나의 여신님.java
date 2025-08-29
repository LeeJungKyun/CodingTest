import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 수연이 : S
 * 여신 : D
 * 돌 : X
 * 악마 : *
 *
 * 수연이가 여신까지 도달하는데 악마는 1초에 한번씩 영역들을 4방향으로 넓힌다.
 * 
 * 1. n, m 입력
 * 2. 지도 입력
 * 3. 일단 악마의 손아귀를 다 퍼뜨리고 퍼뜨리는 일자를 저장
 * 
 * 4. escape() 함수 실행 -> boolean 타입으로
 */
public class Solution {
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	
	static class Point{
		int x, y, day;

		public Point(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	static int testCase;
	static int n, m;
	static char[][] map;
	static int[][] devilTime;
	static ArrayList<Point> devilList;
	static int minTime = 0;
	static Point start, end;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//1. n, m 입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			//2. 지도 입력
			devilList = new ArrayList<>();
			map = new char[n][m];
			for(int i = 0; i < n; i++) {
				String line = br.readLine();
				for(int j = 0; j < m; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == 'S')
						start = new Point(i, j, 0);
					if(map[i][j] == 'D')
						end = new Point(i, j, 0);
					if(map[i][j] == '*')
						devilList.add(new Point(i, j, 0));						
				}
			}
			
			
			//3. 일단 악마의 손아귀를 다 퍼뜨리고 퍼뜨리는 일자를 저장
			spreadDevil();
			sb.append('#').append(tc).append(' ').append(escape() ? minTime : "GAME OVER").append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void spreadDevil() {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		devilTime = new int[n][m];
		//일단 전부 다 무한대로 초기화
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < m; j++)
				devilTime[i][j] = MAX;
		
		for(Point devil : devilList) {
			visited[devil.x][devil.y] = true;
			queue.add(devil);
			devilTime[devil.x][devil.y] = 0;
		}
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				//범위 밖 또는 돌은 못가요잉
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 'X' || map[nx][ny] == 'D')
					continue;

				//방문 안했으면 일수 저장하고 큐에 추가
				if(!visited[nx][ny]) {
					devilTime[nx][ny] = cur.day + 1;
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny, cur.day + 1));
				}
			}
		}
	}
	
	//devilTime을  보면서 탈출 시도
	//현재의 시간이 devilTime 미만이면 갈 수 있음
	public static boolean escape() {
		
		boolean[][] visited = new boolean[n][m];
		ArrayDeque<Point> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.x][start.y] = true;
		
		//BFS시작
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				//일단 범위 밖, 돌 체크해서 가지 않기
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 'X')
					continue;
				
				//여신은 감염 안되니깐 그냥 ㄱㄱ
				if(nx == end.x && ny == end.y) {
					minTime = cur.day + 1;
					return true;
				}
				
				//그 중에서 방문도 안했고 devilTime보다 현재의 시간확인
				if(!visited[nx][ny] && cur.day + 1 < devilTime[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny, cur.day + 1));
				}
			}
		}
		return false;
	}
}