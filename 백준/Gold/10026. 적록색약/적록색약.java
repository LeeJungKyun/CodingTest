import java.io.*;
import java.util.*;

/**
 * @author 이정균 R : 0 G : 1 B : 2
 */
public class Main {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static ArrayDeque<Point> queue;
	static int[][] normalArr, weakArr;
	static boolean[][] visited;
	static int n;
	static int normalCount = 0, weakCount = 0;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		normalArr = new int[n][n];
		weakArr = new int[n][n];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < n; j++) {
				char ch = input.charAt(j);
				if (ch == 'R') {
					normalArr[i][j] = 0;
					weakArr[i][j] = 0;
				} else if (ch == 'G') {
					normalArr[i][j] = 1;
					weakArr[i][j] = 0;
				} else {
					normalArr[i][j] = 2;
					weakArr[i][j] = 2;
				}
			}
		}

		// normalArr 구역 확인
		visited = new boolean[n][n];
		queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					int curColor = normalArr[i][j];
					visited[i][j] = true;
					queue.add(new Point(i, j));
					while (!queue.isEmpty()) {
						Point cur = queue.poll();

						for (int dir = 0; dir < 4; dir++) {
							int nx = cur.x + dx[dir];
							int ny = cur.y + dy[dir];

							if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]
									&& normalArr[nx][ny] == curColor) {
								queue.add(new Point(nx, ny));
								visited[nx][ny] = true;
							}
						}
					}
					normalCount++;
				}
			}
		}

		// weakArr 구역 확인
		visited = new boolean[n][n];
		queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					int curColor = weakArr[i][j];
					visited[i][j] = true;
					queue.add(new Point(i, j));
					while (!queue.isEmpty()) {
						Point cur = queue.poll();

						for (int dir = 0; dir < 4; dir++) {
							int nx = cur.x + dx[dir];
							int ny = cur.y + dy[dir];

							if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]
									&& weakArr[nx][ny] == curColor) {
								queue.add(new Point(nx, ny));
								visited[nx][ny] = true;
							}
						}
					}
					weakCount++;
				}
			}
		}
		
		System.out.println(normalCount + " " + weakCount);
	}
}