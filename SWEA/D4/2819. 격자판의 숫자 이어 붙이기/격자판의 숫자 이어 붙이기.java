import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * dfs로 depth가 7일때마다 HashSet에 넣고 사이즈 출력
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
	static int testCase;
	static int[][] arr;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static HashSet<String> set;
	static final int SIZE = 4;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			set = new HashSet<>();
			
			arr = new int[SIZE][SIZE];
			for(int i = 0; i < SIZE; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < SIZE; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					makeNumber(new Point(i, j), "");
				}
			}
			
			sb.append('#').append(tc).append(' ').append(set.size()).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void makeNumber(Point point, String cur) {
		//7자리의 숫자를 완성한 경우
		if(cur.length() == 7) {
			set.add(cur);
			return;
		}
		
		//탐색 시작
		for(int dir = 0; dir < 4; dir++) {
			int nx = point.x + dx[dir];
			int ny = point.y + dy[dir];
			
			//범위 밖
			if(nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) continue;
			
			//붙이고 다음곳으로 ㄱㄱ
			makeNumber(new Point(nx, ny), cur + String.valueOf(arr[nx][ny]));
		}
	}
}