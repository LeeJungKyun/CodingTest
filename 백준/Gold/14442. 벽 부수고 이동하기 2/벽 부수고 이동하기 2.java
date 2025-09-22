import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Main {
	// 클래스 선언부
	static class Point {
		int x, y, breakCount, moveCount;

		public Point(int x, int y, int breakCount, int moveCount) {
			super();
			this.x = x;
			this.y = y;
			this.breakCount = breakCount;
			this.moveCount = moveCount;
		}
	}

	// 상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	// 변수 선언부
	static int n, m, k;
	static int[][] arr;
	static boolean[][][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Point start, dest;
	static ArrayDeque<Point> queue;

	// 입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 코드 작성 시작
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		// 시작점과 도착지 초기화
		start = new Point(0, 0, k, 1);
		dest = new Point(n - 1, m - 1, 0, 0);
		visited = new boolean[n][m][k + 1];
		
		queue = new ArrayDeque<>();
		queue.add(start);
		visited[0][0][k] = true;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.x == dest.x && cur.y == dest.y) {
				System.out.println(cur.moveCount);
				return;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				// 이동할 곳이 벽인 경우
                if (arr[nx][ny] == 1) {
                    if (cur.breakCount > 0 && !visited[nx][ny][cur.breakCount - 1]) {
                        visited[nx][ny][cur.breakCount - 1] = true;
                        queue.add(new Point(nx, ny, cur.breakCount - 1, cur.moveCount + 1));
                    }
                } 
                // 이동할 곳이 길인 경우
                else {
                    if (!visited[nx][ny][cur.breakCount]) {
                        visited[nx][ny][cur.breakCount] = true;
                        queue.add(new Point(nx, ny, cur.breakCount, cur.moveCount + 1));
                    }
                }
			}
		}
		
		System.out.println(-1);
	}
}