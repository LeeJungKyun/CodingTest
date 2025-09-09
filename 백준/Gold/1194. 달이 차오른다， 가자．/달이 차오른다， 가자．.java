import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 빈칸 .
 * 벽 #
 * 열쇠 a ~ f
 * 문 A ~ F
 * 시작 0
 * 출구 1
 * 
 * Point에다가 좌표, 시간, key(비트마스킹으로 표시)
 * 
 * 1. 높이, 너비 입력
 * 2. 배열 입력
 * 3. 시작점부터 BFS시작
 * 	3-1. 다음 방문 좌표가 1인지 확인 후 출력
 * 	3-2. 다음 칸이 열쇠인 경우 ('a' ~ 'f') 현재 포인트에 keys 마스킹
 *  3-3. 다음 칸이 문인 경우 ('A' ~ 'F') 내 마스킹된거로 딸 수 있는 문인지 확인
 */
public class Main {
	static class Point {
		int x, y, time, keys;

		public Point(int x, int y, int time, int keys) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.keys = keys;
		}
		
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int height, width;
	static char[][] arr;
	static boolean[][][] visited;
	static HashSet<Character> keySet;
	static Point start;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 높이, 너비 입력
		st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		//2. 배열 입력
		arr = new char[height][width];
		for(int i = 0; i < height; i++) {
			String line = br.readLine();
			for(int j = 0; j < width; j++) {
				arr[i][j] = line.charAt(j);
				if(arr[i][j] == '0') start = new Point(i, j, 0, 0);
			}
		}
		
		keySet = new HashSet<>();
		ArrayDeque<Point> queue = new ArrayDeque<>();
		queue.add(start);
		visited = new boolean[height][width][64];
		visited[start.x][start.y][0] = true;
		
		//3. 시작점부터 BFS시작
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			
			//3-1. 다음 방문 좌표가 1인지 확인 후 출력
		    if (arr[cur.x][cur.y] == '1') {
		        System.out.println(cur.time);
		        return;
		    }

			//다음 방문 좌표
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				//범위 밖 확인
				if(nx < 0 || nx >= height || ny < 0 || ny >= width) continue;
				
				char nextCell = arr[nx][ny];
				
				//벽인지 확인
				if(nextCell == '#') continue;
				
				//3-2. 다음 칸이 열쇠인 경우 ('a' ~ 'f') 현재 포인트에 keys 마스킹
		        if (nextCell >= 'a' && nextCell <= 'f') {
		            //새롭게 얻은 키 마스킹
		            int newKey = cur.keys | (1 << (nextCell - 'a'));
		            if (!visited[nx][ny][newKey]) {
		                visited[nx][ny][newKey] = true;
		                queue.add(new Point(nx, ny, cur.time + 1, newKey));
		            }
		        }
		        //3-3. 다음 칸이 문인 경우 ('A' ~ 'F') 내 마스킹된거로 딸 수 있는 문인지 확인
		        else if (nextCell >= 'A' && nextCell <= 'F') {
		            // 현재 키 상태로 문을 열 수 있는지 확인
		            int doorBit = 1 << (nextCell - 'A');
		            //내가 열쇠를 보유하고, 방문하지 않았다면
		            if ((cur.keys & doorBit) != 0 && !visited[nx][ny][cur.keys]) {
		                visited[nx][ny][cur.keys] = true;
		                queue.add(new Point(nx, ny, cur.time + 1, cur.keys));
		            }
		        }
		        //아무것도 아니고 그냥 갈 수 있는 곳이면
		        else {
		            if (!visited[nx][ny][cur.keys]) {
		                visited[nx][ny][cur.keys] = true;
		                queue.add(new Point(nx, ny, cur.time + 1, cur.keys));
		            }
		        }
			}
		}
		
		//BFS 로직 끝
		System.out.println(-1);
		
	}
}