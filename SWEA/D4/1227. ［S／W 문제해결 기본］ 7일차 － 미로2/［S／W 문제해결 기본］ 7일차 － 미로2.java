import java.io.*;
import java.util.*;

public class Solution {
	static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static final int TEST_CASE = 10;
	static final int SIZE = 100;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] maze;
	static boolean isEscape;
	static boolean[][] visited;
	static Point start, end;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= TEST_CASE; tc++) {
			isEscape = false;
			
			//테케 입력
			br.readLine();
			
			maze = new int[SIZE][SIZE];
			visited = new boolean[SIZE][SIZE];
			for(int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for(int j = 0; j < SIZE; j++) {
					maze[i][j] = line.charAt(j) - '0';
					if(maze[i][j] == 2)
						start = new Point(i, j);
					if(maze[i][j] == 3)
						end = new Point(i, j);
				}
			}
			visited[start.x][start.y] = true;
			isEscape = findPath(start.x, start.y);
			
			sb.append('#').append(tc).append(' ').append(isEscape ? 1 : 0).append('\n');
		}
		System.out.println(sb);
	}
	
	public static boolean findPath(int x, int y) {
		if(x == end.x && y == end.y) {
			return true;
		}
		
		for(int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			//maze내에 있고 길이라면
			if(0 <= nx && nx < SIZE && 0 <= ny && ny < SIZE && maze[nx][ny] != 1 && !visited[nx][ny]) {
				visited[nx][ny] = true;
				if(findPath(nx, ny)) return true;
			}
		}
		return false;
	}
}