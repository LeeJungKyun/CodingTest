import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N인 정사각형 모양의 치즈 -> N x N 에 맛있는 정도가 1부터 100사이로 입력
 * 100일동안 X일에 X번호가 씌여진 치즈를 먹음
 * 덩어리가 제일 많은 날의 덩어리 개수를 출력
 * 
 * 100일 동안 루프 돌면서 번호를 0으로 바꾸고 덩어리 개수 세기..?
 *
 */

public class Solution {
	static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int testCase, n;
	static int maxCount;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static ArrayDeque<Point> queue;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			maxCount = Integer.MIN_VALUE;
			
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int day = 0; day <= 100; day++) {		
				int curDayCount = 0;
				visited = new boolean[n][n];
				
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						//덩어리 시작
						if(arr[i][j] > day && !visited[i][j]) {
							queue = new ArrayDeque<>();
							queue.add(new Point(i, j));
							visited[i][j] = true;
							while(!queue.isEmpty()) {
								Point cur = queue.poll();
								for(int dir = 0; dir < 4; dir++) {
									int nx = cur.x + dx[dir];
									int ny = cur.y + dy[dir];
									
									if(0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] > day) {
										if(!visited[nx][ny]) {
											queue.add(new Point(nx, ny));
											visited[nx][ny] = true;
										}
									}
								}
							}
							curDayCount++;
						}
					}
				}
				
				maxCount = Math.max(maxCount, curDayCount);
			}
			
			sb.append('#').append(tc).append(' ').append(maxCount).append('\n');
		}
		
		System.out.println(sb);
	}
}
