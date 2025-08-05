import java.io.*;
import java.util.*;

public class Main {
	static int[] dice = new int[7];
	static int n, m, x, y, k;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while(k-- > 0) {
			int cmd = Integer.parseInt(st.nextToken());
			moveDice(cmd);
		}
	}
	
	public static void moveDice(int dir) {
		//유효성 검사
		int nx = x + dx[dir - 1];
		int ny = y + dy[dir - 1];
		
		if(nx < 0 || nx >= n || ny < 0 || ny >= m)
			return;
		rollDice(dir, nx, ny);
		x = nx; y = ny;
		
	}
	
	static void rollDice(int dir, int x, int y) {
		int tmp = dice[3];
		switch(dir) {
		case 1:
			dice[3] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
			break;
		case 2:
			dice[3] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
			break;
		case 3:
			dice[3] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[1];
			dice[1] = tmp;
			break;
		case 4:
			dice[3] = dice[1];
			dice[1] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
			break;
		}
		if(map[x][y] == 0) {
			map[x][y] = dice[6];
		} else {
			dice[6] = map[x][y];
			map[x][y] =0;
		}
		System.out.println(dice[3]);
		
	}
}
