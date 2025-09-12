import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * BFSㄱㄱ
 *
 */
public class Main {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int n, m, k;
	static int[][] arr;
	static ArrayList<Integer> areaList;
	static boolean[][] visited;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//세로, 가로, 직사각형의 개수 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		arr = new int[n][m];
		
		//직사각형 좌표 받아서 채워넣기
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			
			//왼쪽 아래
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			//오른쪽 위
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int row = y1; row < y2 ; row++) {
				for(int col = x1; col < x2; col++) {
					arr[row][col] = 1;
				}
			}
		}
		
		visited = new boolean[n][m];
		areaList = new ArrayList<>();
		int count = 0;
		
		for(int i = 0; i < n; i++) {
		    for(int j = 0; j < m; j++){
		        if(arr[i][j] == 0 && !visited[i][j]) {
		            int area = 0;
		            count++;
		            visited[i][j] = true;
		            ArrayDeque<Point> queue = new ArrayDeque<>();
		            queue.add(new Point(i, j));

		            while(!queue.isEmpty()) {
		                Point cur = queue.poll();
		                area++;

		                for(int dir = 0; dir < 4; dir++) {
		                    int nx = cur.x + dx[dir];
		                    int ny = cur.y + dy[dir];

		                    if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
		                    if(visited[nx][ny]) continue;
		                    if(arr[nx][ny] == 1) continue;

		                    queue.add(new Point(nx, ny));
		                    visited[nx][ny] = true;
		                }
		            }

		            areaList.add(area);
		        }
		    }
		}

		
		Collections.sort(areaList);
		System.out.println(count);
		for(int num : areaList)
			System.out.print(num + " ");
	}
}