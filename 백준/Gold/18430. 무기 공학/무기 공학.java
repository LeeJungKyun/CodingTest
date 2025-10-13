import java.io.*;
import java.util.*;

public class Main {
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
	static int n, m;
	static int[][] arr;
	static boolean[][] visited;
	static int maxScore = MIN;
	
	static final int[][][] boomerangs = {
		    { {-1, 0}, {0, 1} },  // 위 + 오른쪽
		    { {0, 1}, {1, 0} },   // 오른쪽 + 아래
		    { {1, 0}, {0, -1} },  // 아래 + 왼쪽
		    { {0, -1}, {-1, 0} }, // 왼쪽 + 위
		};
	
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n, m 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//2. 배열 입력
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[n][m];
		
		findMax(0, 0, 0);
		
		System.out.println(maxScore);
	}
	
	public static void findMax(int x, int y, int score) {
		//종료조건
		if(x == n) {
			maxScore = Math.max(score, maxScore);
			return;
		}
		
		//일단 현재 좌표 방문 안했으면
		if (!visited[x][y]) {
		    for (int[][] boomerang : boomerangs) {
		        int nx1 = x + boomerang[0][0];
		        int ny1 = y + boomerang[0][1];
		        int nx2 = x + boomerang[1][0];
		        int ny2 = y + boomerang[1][1];

		        if (isPossible(nx1, ny1, nx2, ny2)) {
		            visited[x][y] = true;
		            visited[nx1][ny1] = true;
		            visited[nx2][ny2] = true;

		            int newScore = score + arr[x][y] * 2 + arr[nx1][ny1] + arr[nx2][ny2];

		            int nextX = x;
		            int nextY = y + 1;
		            if (nextY == m) {
		                nextX++;
		                nextY = 0;
		            }

		            findMax(nextX, nextY, newScore);

		            visited[x][y] = false;
		            visited[nx1][ny1] = false;
		            visited[nx2][ny2] = false;
		        }
		    }
		}

		
		if(y == m - 1) {
			findMax(x + 1, 0, score);
		} else findMax(x, y + 1, score);
	}

	public static boolean isPossible(int x1, int y1, int x2, int y2) {
		if(x1 < 0 || y1 < 0 || x1 >= n || y1 >= m) return false;
		
		if(x2 < 0 || y2 < 0 || x2 >= n || y2 >= m) return false;
		
		if(visited[x1][y1]) return false;
		
		if(visited[x2][y2]) return false;
		
		return true;
	}
}