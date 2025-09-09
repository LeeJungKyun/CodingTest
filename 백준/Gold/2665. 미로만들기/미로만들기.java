import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * n x n 바둑판 모양에 방 (0 검은방, 1 흰 방)
 * 1이 갈 수 있는 곳임
 *
 * 1. n입력
 * 2. 방의 정보를 입력받음
 * 		2-1. 검은 방의 개수를 blackCount에 저장
 * 		2-2. 검은 방의 좌표들을 ArrayList에 저장
 * 3. 바로 탈출 할 경우도 있으니깐 일단 BFS돌려보기
 * 4. 탈출하지 못한다면 changeCount를 증가시켜서 검은 방 중에서 changeCount만큼 흰색 방으로 바꾸는 백트래킹 함수 실행
 * 		4-1. 백트래킹 함수 내에서 depth가 changeCount가 되었다면 BFS 실행
 * 		4-2. BFS를 실행해서 탈출이 된다면 바로 changeCount 출력하고 종료하기
 */
public class Main {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n;
	static int blackCount;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] arr;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				arr[i][j] = line.charAt(j) - '0';
				if(arr[i][j] == 0) {
				}
			}
		}
		
		tryEscape();
	}
	
	//tempArr를 사용해서 start -> End 탈출시도
	public static void tryEscape() {
		int[][] dist = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		ArrayDeque<Point> deque = new ArrayDeque<>();
		
		dist[0][0] = 0;
		deque.add(new Point(0, 0));
		
		while(!deque.isEmpty()) {
			Point cur = deque.poll();
			
			if(cur.x == n - 1 && cur.y == n - 1) {
				System.out.println(dist[n - 1][n - 1]);
				return;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				//방 벗어나면 이번 좌표 벗어나기
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				
				// 흰 방인 경우
                if (arr[nx][ny] == 1) {
                    if (dist[nx][ny] > dist[cur.x][cur.y]) {
                        dist[nx][ny] = dist[cur.x][cur.y];
                        deque.addFirst(new Point(nx, ny));
                    }
                }
                
                // 검은 방인 경우
                else {
                    if (dist[nx][ny] > dist[cur.x][cur.y] + 1) {
                        dist[nx][ny] = dist[cur.x][cur.y] + 1;
                        deque.addLast(new Point(nx, ny));
                    }
                }
			}
		}
	}
}