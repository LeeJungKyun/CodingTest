import java.io.*;
import java.util.*;

public class Solution {
	static class Tank{
		int x, y, dir;

		public Tank(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	static int testCase;
	static int h, w, n;
	static String cmd;
	static char[][] map;
	static Tank tank;
	//상0 하1 좌2 우3
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h][w];
			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == '^') {
						tank = new Tank(i, j, 0);
					} else if(map[i][j] == 'v') {
						tank = new Tank(i, j, 1);
					} else if(map[i][j] == '<') {
						tank = new Tank(i, j, 2);
					} else if(map[i][j] == '>') {
						tank = new Tank(i, j, 3);
					}
				}
			}

			n = Integer.parseInt(br.readLine());
			cmd = br.readLine();

			for (int i = 0; i < n; i++) {
				char command = cmd.charAt(i);
				simulate(command);
			}
			
			sb.append('#').append(tc).append(' ');
			
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

	public static void simulate(char cmd) {
		int nx, ny;
		switch (cmd) {
		// 방향 바꾸고, 평지라면 이동
		case 'U':
			tank.dir = 0;
			nx = tank.x + dx[tank.dir];
			ny = tank.y + dy[tank.dir];
			if(isInBound(nx, ny) && map[nx][ny] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x = nx;
				tank.y = ny;
			}
			// 이 시점에서 tank.x, tank.y는 이미 새로운 위치!
			map[tank.x][tank.y] = dirToChar(tank.dir);
			break;
		// 방향 바꾸고, 평지라면 이동
		case 'D':
			tank.dir = 1;
			nx = tank.x + dx[tank.dir];
			ny = tank.y + dy[tank.dir];
			if(isInBound(nx, ny) && map[nx][ny] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x = nx;
				tank.y = ny;
			}
			map[tank.x][tank.y] = dirToChar(tank.dir);
			break;
		// 방향 바꾸고, 평지라면 이동
		case 'L':
			tank.dir = 2;
			map[tank.x][tank.y] = dirToChar(tank.dir);
			nx = tank.x + dx[tank.dir];
			ny = tank.y + dy[tank.dir];
			if(isInBound(nx, ny) && map[nx][ny] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x = nx;
				tank.y = ny;
			}
			// 이 시점에서 tank.x, tank.y는 이미 새로운 위치!
			map[tank.x][tank.y] = dirToChar(tank.dir);
			break;
		// 방향 바꾸고, 평지라면 이동
		case 'R':
			tank.dir = 3;
			map[tank.x][tank.y] = dirToChar(tank.dir);
			nx = tank.x + dx[tank.dir];
			ny = tank.y + dy[tank.dir];
			if(isInBound(nx, ny) && map[nx][ny] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x = nx;
				tank.y = ny;
			}
			// 이 시점에서 tank.x, tank.y는 이미 새로운 위치!
			map[tank.x][tank.y] = dirToChar(tank.dir);
			break;
		// 포탄발사
		case 'S':
			int shootingDir = tank.dir;
			int curX = tank.x;
			int curY = tank.y;
			while(true) {
				int nextX = curX + dx[shootingDir];
				int nextY = curY + dy[shootingDir];
				
				if(isInBound(nextX, nextY)) {
					//평지인 경우
					if(map[nextX][nextY] == '.' || map[nextX][nextY] == '-') {
						curX = nextX;
						curY = nextY;
					}
					//강철인 경우
					else if(map[nextX][nextY] == '#') {
						break;
					} else if(map[nextX][nextY] == '*') {
						map[nextX][nextY] = '.';
						break;
					}
				} else {
					break;
				}
			}
			break;
		}
	}
	
	public static char dirToChar(int dir) {
		switch(dir) {
			case 0: return '^';
			case 1: return 'v';
			case 2: return '<';
			case 3: return '>';
			default: return '?';
		}
	}
	
	public static boolean isInBound(int x, int y) {
		return(0 <= x && x < h && 0 <= y && y < w);
	}
}
