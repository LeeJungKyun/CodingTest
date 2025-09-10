import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static class User {
		int x, y, time;
		public User(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}	
	}
	
	static char[][] arr;
	static int[][] waterReachTime;
	static boolean[][] visited;
	static int r, c;
	static Point end;
	static User user;
	static ArrayList<Point> waters;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new char[r][c];
		waters = new ArrayList<>();
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			for(int j = 0; j < c; j++) {
				arr[i][j] = line.charAt(j);
				if(arr[i][j] == 'S')
					user = new User(i, j, 0);
				else if(arr[i][j] == 'D')
					end = new Point(i, j);
				else if(arr[i][j] == '*') {
					waters.add(new Point(i, j));
				}
			}
		}
		
		//물의 이동시간 구하기
		ArrayDeque<Point> queue = new ArrayDeque<>();
		visited = new boolean[r][c];
		waterReachTime = new int[r][c];
		for(int i = 0; i < r; i++)
			Arrays.fill(waterReachTime[i], INF);
		
		for(Point water : waters) {
			queue.add(water);
			visited[water.x][water.y] = true;
			waterReachTime[water.x][water.y] = 0;
		}
		
		while(!queue.isEmpty()) {
			Point curWater = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = curWater.x + dx[dir];
				int ny = curWater.y + dy[dir];
				
				//범위 밖 체크
				if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
				
				if(visited[nx][ny] || arr[nx][ny] == 'D' || arr[nx][ny] == 'X') continue;
				
				visited[nx][ny] = true;
				waterReachTime[nx][ny] = waterReachTime[curWater.x][curWater.y] + 1;
				queue.add(new Point(nx, ny));
			}
		}
		
		//탈출 시작
		ArrayDeque<User> userQueue = new ArrayDeque<>();
		visited = new boolean[r][c];
		visited[user.x][user.y] = true;
		userQueue.add(user);
		while(!userQueue.isEmpty()) {
			User curUser = userQueue.poll();
			
			if(curUser.x == end.x && curUser.y == end.y) {
				System.out.println(curUser.time);
				return;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = curUser.x + dx[dir];
				int ny = curUser.y + dy[dir];
				
				//범위 밖 체크
				if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
				
				if(visited[nx][ny]|| arr[nx][ny] == 'X') continue;
				
				if(waterReachTime[nx][ny] <= curUser.time + 1) continue;
				
				visited[nx][ny] = true;
				userQueue.add(new User(nx, ny, curUser.time + 1));
			}
		}
		System.out.println("KAKTUS");
	}
}