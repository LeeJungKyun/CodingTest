import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 토네이도는 격자의 가운데부터 시작
 * 이동은 L, D, R, U 순서대로 (1, 1, 2, 2, 3, 3, ... ) 이런식으로 이동
 * 총 이동 횟수는 2 * n - 1
 * */
public class Main {
	static final int L = 0;
	static final int D = 1;
	static final int R = 2;
	static final int U = 3;
	
	static class Tornado {
		int x, y, dir;

		public Tornado(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static int n;
	static int startAmount, endAmount;
	static int[][] arr;
	// 모래 분산 비율: 1%, 1%, 2%, 2%, 7%, 7%, 10%, 10%, 5%
	static double[] ratio = {0.01, 0.01, 0.02, 0.02, 0.07, 0.07, 0.1, 0.1, 0.05};
	
	// 방향: 좌(L), 하(D), 우(R), 상(U)
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	// 순서: 1%, 1%, 2%, 2%, 7%, 7%, 10%, 10%, 5%
	static int[] leftDx = {-1, 1, -2, 2, -1, 1, -1, 1, 0};
	static int[] leftDy = {1, 1, 0, 0, 0, 0, -1, -1, -2};
	
	static int[] rightDx = {-1, 1, -2, 2, -1, 1, -1, 1, 0};
	static int[] rightDy = {-1, -1, 0, 0, 0, 0, 1, 1, 2};
	
	static int[] upDx = {1, 1, 0, 0, 0, 0, -1, -1, -2};
	static int[] upDy = {1, -1, 2, -2, 1, -1, 1, -1, 0};
	
	static int[] downDx = {-1, -1, 0, 0, 0, 0, 1, 1, 2};
	static int[] downDy = {-1, 1, -2, 2, -1, 1, -1, 1, 0};
	
	// (기존 코드에서 사용되지 않는 배열은 그대로 둡니다.)
	static int[][] relativeDir = {
			//현재 보고 있는쪽이 왼쪽일때
			{D, R, U, L},
			//현재 보고 있는쪽이 아래쪽일때
			{R, U, L, D},
			//현재 보고 있는쪽이 오른쪽일때
			{U, L, D, R},
			//현재 보고 있는쪽이 위쪽일때
			{L, D, R, U}
	};
	static int outSand = 0;
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x = n / 2;
		int y = n / 2;
		
		// 토네이도 이동 시뮬레이션
		int dir = L; // 초기 방향: 좌(L)
		int moveCount = 1; // 이동 칸 수
		
		while(true) {
			for(int i = 0; i < 2; i++) { // 두 번씩 이동 (1, 1, 2, 2, ...)
				for(int j = 0; j < moveCount; j++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					// 토네이도 이동 후 모래 분산
					if (arr[nx][ny] > 0) {
						spreadSand(nx, ny, dir);
					}
					
					x = nx;
					y = ny;
					
					if (x == 0 && y == 0) {
						System.out.println(outSand);
						return;
					}
				}
				dir = (dir + 1) % 4;
			}
			moveCount++;
		}
	}
	
	public static void spreadSand(int sx, int sy, int dir) {
		int sand = arr[sx][sy];
		int totalMoved = 0;
		int[] currentDx = null;
		int[] currentDy = null;
		
		switch(dir) {
			case L:
				currentDx = leftDx;
				currentDy = leftDy;
				break;
			case D:
				currentDx = downDx;
				currentDy = downDy;
				break;
			case R:
				currentDx = rightDx;
				currentDy = rightDy;
				break;
			case U:
				currentDx = upDx;
				currentDy = upDy;
				break;
		}

		// 9개 위치로 모래 분산 (비율에 따라)
		// ratio 배열의 크기는 9이므로, i < 9까지 순회
		for(int i = 0; i < 9; i++) { 
			int nx = sx + currentDx[i];
			int ny = sy + currentDy[i];
			// Java에서 실수 곱셈 후 정수 변환 시 소수점 아래는 버려집니다.
			int movedSand = (int) (sand * ratio[i]); 
			
			if (movedSand > 0) {
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
					outSand += movedSand;
				} else {
					arr[nx][ny] += movedSand;
				}
				totalMoved += movedSand;
			}
		}
		
		// 남은 모래(알파) 처리: 토네이도의 이동 방향 바로 앞 칸
		int alphaX = sx + dx[dir];
		int alphaY = sy + dy[dir];
		int remainingSand = sand - totalMoved;
		
		if (alphaX < 0 || alphaX >= n || alphaY < 0 || alphaY >= n) {
			outSand += remainingSand;
		} else {
			arr[alphaX][alphaY] += remainingSand;
		}
		
		// 모래가 분산된 원래 위치는 0이 됩니다.
		arr[sx][sy] = 0;
	}
}