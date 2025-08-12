import java.io.*;
import java.util.*;
public class Solution {
	static int testCase, n, maxCount, minRoomNo;
	static int[][] room;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			maxCount = Integer.MIN_VALUE;
			minRoomNo = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			room = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//입력 끝, 로직 시작
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					findWay(i, j, 1, room[i][j]);
				}
			}
			
			sb.append('#').append(tc).append(' ').append(minRoomNo).append(" ").append(maxCount).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void findWay(int x, int y, int cnt, int startRoomNo) {
		if(cnt > maxCount) {
			minRoomNo = startRoomNo;
			maxCount = cnt;
		} else if(cnt == maxCount) {
			minRoomNo = Math.min(minRoomNo, startRoomNo);
		}
		for(int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			//집을 벗어나가면 나가기
			if(nx < 0 || nx >= n || ny < 0 || ny >= n)
				continue;
			//크기 비교
			if(room[x][y] + 1 == room[nx][ny])
				findWay(nx, ny, cnt + 1, startRoomNo);
		}
	}
}
