import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * Point 객체에 좌표, 말처럼 이동 몇번했는지, 이동 몇번했는지 저장
 * 일반 이동했는지, 말처럼 이동했는지 경우에 따라서 방문
 *
 */
public class Main {
	static class Point{
		int x, y, knightCount, depth;
		public Point(int x, int y, int knightCount, int depth) {
			this.x = x;
			this.y = y;
			this.knightCount = knightCount;
			this.depth = depth;
		}
	}
	static int k, w, h;
	static int[][] arr;
	//x, y, knight
	static boolean[][][] visited;
	static int[] normalDx = {-1, 0, 1, 0};
	static int[] normalDy = {0, -1, 0, 1};
	static int[] knightDx = {-1, -1, -2, -2, 1, 1, 2, 2};
	static int[] knightDy = {-2, 2, -1, 1, -2, 2, -1, 1};
	static ArrayDeque<Point> queue = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		arr = new int[h][w];
		visited = new boolean[h][w][k + 10];
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작점 추가
		queue.add(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//목적지 확인
			if(cur.x == h - 1 && cur.y == w - 1) {
				System.out.println(cur.depth);
				return;
			}

			//일반 이동 계산
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + normalDx[dir];
				int ny = cur.y + normalDy[dir];
				
				if(nx >= 0 && nx < h && ny >= 0 && ny < w && arr[nx][ny] != 1) {
					if(!visited[nx][ny][cur.knightCount]) {
						visited[nx][ny][cur.knightCount] = true;
						queue.add(new Point(nx, ny, cur.knightCount, cur.depth + 1));
					}
				}
			}
			
			//나이트 이동 가능 여부 확인 후 나이트 이동 계산
			if(cur.knightCount < k) {
				for(int dir = 0; dir < 8; dir++) {
					int nx = cur.x + knightDx[dir];
					int ny = cur.y + knightDy[dir];
					
					if(nx >= 0 && nx < h && ny >= 0 && ny < w && arr[nx][ny] != 1) {
						if(!visited[nx][ny][cur.knightCount + 1]) {
							visited[nx][ny][cur.knightCount + 1] = true;
							queue.add(new Point(nx, ny, cur.knightCount + 1, cur.depth + 1));
						}
							
					}
				}
			}
		}
		System.out.println(-1);
	}
}
