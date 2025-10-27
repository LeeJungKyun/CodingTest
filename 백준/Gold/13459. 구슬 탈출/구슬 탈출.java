import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Main {
	//클래스 선언부
	static class Point {
		int x, y, dist;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static class Turn {
		int count;
		Point red, blue;
		public Turn(int count, Point red, Point blue) {
			super();
			this.count = count;
			this.red = red;
			this.blue = blue;
		}
	}
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	static final int RED = 0;
	static final int BLUE = 1;
	//변수 선언부
	static int n, m;
	static char[][] arr;
	// 4차원 방문 배열 [red_x][red_y][blue_x][blue_y]로 수정
	static boolean[][][][] visited; 
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Point red, blue, hole;
	static ArrayDeque<Turn> queue;
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
		
		//2. 배열 선언
		arr = new char[n][m];
		
		//3. 배열 입력
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = line.charAt(j);
				if(arr[i][j] == 'R')
					red = new Point(i, j);
				else if(arr[i][j] == 'B')
					blue = new Point(i, j);
				else if(arr[i][j] == 'O')
					hole = new Point(i, j);
			}
		}
		
		//4. 방문 배열 [row][column][red||blue] 선언
		// 4차원 방문 배열 [red_x][red_y][blue_x][blue_y]로 수정
		visited = new boolean[n][m][n][m];
		
		//5. BFS를 위한 초기화
		queue = new ArrayDeque<>();
		Turn startTurn = new Turn(1, red, blue);
		
		queue.add(startTurn);
		// 4차원 방문 체크로 수정
		visited[startTurn.red.x][startTurn.red.y][startTurn.blue.x][startTurn.blue.y] = true;
		
		while(!queue.isEmpty()) {
			Turn cur = queue.poll();
			
			if (cur.count > 10) continue;

			for (int dir = 0; dir < 4; dir++) {
			    Point nextRed, nextBlue;

			    // 1. 방향에 따른 우선순위 판단
			    if (dir == 0) { // 위
			        if (cur.red.x < cur.blue.x) { 
			            nextRed = tilt(cur.red, dir);
			            nextBlue = tilt(cur.blue, dir);
			        } else {
			            nextBlue = tilt(cur.blue, dir);
			            nextRed = tilt(cur.red, dir);
			        }
			    } else if (dir == 1) { // 아래
			        if (cur.red.x > cur.blue.x) { 
			            nextRed = tilt(cur.red, dir);
			            nextBlue = tilt(cur.blue, dir);
			        } else {
			            nextBlue = tilt(cur.blue, dir);
			            nextRed = tilt(cur.red, dir);
			        }
			    } else if (dir == 2) { // 왼쪽
			        if (cur.red.y < cur.blue.y) { 
			            nextRed = tilt(cur.red, dir);
			            nextBlue = tilt(cur.blue, dir);
			        } else {
			            nextBlue = tilt(cur.blue, dir);
			            nextRed = tilt(cur.red, dir);
			        }
			    } else { // 오른쪽
			        if (cur.red.y > cur.blue.y) { 
			            nextRed = tilt(cur.red, dir);
			            nextBlue = tilt(cur.blue, dir);
			        } else {
			            nextBlue = tilt(cur.blue, dir);
			            nextRed = tilt(cur.red, dir);
			        }
			    }

			    // 파란 구슬 빠지면 실패
			    if (arr[nextBlue.x][nextBlue.y] == 'O') continue;

			    // 빨간 구슬 성공
			    if (arr[nextRed.x][nextRed.y] == 'O') {
			        System.out.println(1);
			        return;
			    }

			    // 4. 겹칠 경우 조정
			    if (nextRed.x == nextBlue.x && nextRed.y == nextBlue.y) {
			    	if (nextRed.dist > nextBlue.dist) {
			            nextRed.x -= dx[dir];
			            nextRed.y -= dy[dir];
			        } else {
			            nextBlue.x -= dx[dir];
			            nextBlue.y -= dy[dir];
			        }
			    }

			    if (!visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y]) {
			        visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y] = true;
			        queue.add(new Turn(cur.count + 1, nextRed, nextBlue));
			    }
			}

		}
		
		// 큐가 비었는데 성공하지 못했으면 0 출력
		System.out.println(0);
	}
	
	public static Point tilt(Point point, int dir) {
		int x = point.x;
		int y = point.y;
		int dist = 0;
		
		// 다음 칸이 벽('#')이 아닐 때까지 이동
		while(arr[x + dx[dir]][y + dy[dir]] != '#') {
			x += dx[dir];
			y += dy[dir];
			dist++;
			// 구멍('O')에 빠졌으면 더 이상 진행하지 않고 반복문 탈출
			if (arr[x][y] == 'O') {
				break;
			}
		}
		return new Point(x, y, dist);
	}

}