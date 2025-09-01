import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N x M 크기의 집 -> 빈 칸 또는 벽
 * 1초에 최대 K 칸 상, 하, 좌, 우
 * 다른 방향으로 바꾸려면 반드시 방향을 바꾸려는 위치에서 멈춰야 함
 * (r1, c1) -> (r2, c2) 로 이동하려고 할 때, 최소 시간을 구하고 이동할 수 없다면 -1
 * 
 * Point 객체 -> 좌표만, Robot객체 -> 좌표 + 시간
 *
 *	1. n, m, k입력
 *	2. 배열 입력 (벽은 1, 빈 공간 0)
 *	3. 시작점, 목적지 입력(0-base 로 변환)
 */
public class Main {
	static final int MAX_SECONDS = 1_000_000;
	static class Robot {
		int x, y, time;

		public Robot(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static Point start, end;
	//시간까지 관리할 visited 배열
	static int[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int n, m, k;
	static int[][] arr;
	static BufferedReader br;
	static StringTokenizer st;
	

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. n, m, k 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//2. 배열 입력
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				if(input.charAt(j) == '#')
					arr[i][j] = 1;
				else arr[i][j] = 0;
			}
		}
		
		//3. 시작점, 목적지 입력 (0-base로 변환)
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken()) - 1;
		int y1 = Integer.parseInt(st.nextToken()) - 1;
		int x2 = Integer.parseInt(st.nextToken()) - 1;
		int y2 = Integer.parseInt(st.nextToken()) - 1;
		start = new Point(x1, y1);
		end = new Point(x2, y2);
		
		//0초인 상태의 로봇에서 시작
		Robot robot = new Robot(start.x, start.y, 0);

		// 4. 방문 시간 기록 배열
        visited = new int[n][m];
        for (int[] row : visited) Arrays.fill(row, Integer.MAX_VALUE);
		
		ArrayDeque<Robot> queue = new ArrayDeque<>();
		queue.offer(robot);
		visited[start.x][start.y] = 0;
		//System.out.printf("%d, %d 에서 시작\n", robot.x, robot.y);
		while (!queue.isEmpty()) {
            Robot cur = queue.poll();

            // 도착
            if (cur.x == end.x && cur.y == end.y) {
                System.out.println(cur.time);
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                for (int dist = 1; dist <= k; dist++) {
                    int nx = cur.x + dx[dir] * dist;
                    int ny = cur.y + dy[dir] * dist;

                    // 범위, 벽 체크
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                    if (arr[nx][ny] == 1) break;

                    // 현재보다 같거나 더 적은 시간에 이미 방문했으면 가지 않음
                    if (visited[nx][ny] < cur.time + 1) break;
                    if (visited[nx][ny] == cur.time + 1) continue;

                    visited[nx][ny] = cur.time + 1;
                    queue.add(new Robot(nx, ny, cur.time + 1));
                }
            }
        }
		System.out.println(-1);
	}
}
