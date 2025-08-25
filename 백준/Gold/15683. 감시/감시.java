import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 0: 빈 공간
 * 1 ~ 5: CCTV (1: <3>, 2: <2, 3>, 3: <0, 3>, 4: <0, 2, 3>, 5: <0, 1, 2, 3>)
 * 6: 벽
 * 
 *         CCTV의 개수는 최대 8개 사각 지대의 최소 크기를 구해라
 * 
 *         CCTV마다 4방향 가능 -> 4^8 = 65536 마다 N x M 탐색 -> 2^22 얼마 안됨 4진수를 사용해서 각
 *         CCTV의 상태를 저장하는 배열을 사용해서 완탐 ㄱㄱ
 * 
 * 
 *         1. n, m입력 
 *         2. 배열 초기화 
 *         3. CCTV리스트에 넣기 
 *         4. 각 CCTV가 보는 방향을 저장하는 mask 변수 -> 각 CCTV마다 2비트 1 : << (2 * cctv개수) 
 *         5. 조합이 정해졌으면 tempArr에 기존 배열 값 복사
 *         6. 복사해놓은 배열을 사용해서 각 CCTV마다 방향을 감시했다고 7 체크
 *         	6-1. mask를 temp에 복사하고 그거를 4로 나눈 나머지가 현재 보고 있는 방향
 *         	6-2. temp를 4로 나눠놓기
 *         	6-3. 현재 cctv의 좌표 가져오기
 *         	6-4. 각 cctv의 번호를 기준으로 방향탐색
 *         7. 감시 영역 갱신
 * 
 *
 */
public class Main {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int n, m;
	static int[][] arr, tempArr;
	static int minWatch = Integer.MAX_VALUE;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<Point> cctvList = new ArrayList<>();
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 1. n, m입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 2. 배열 초기화
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				// 3. CCTV리스트에 넣기
				if (1 <= arr[i][j] && arr[i][j] <= 5)
					cctvList.add(new Point(i, j));
			}
		}

		// 4. 각 CCTV가 보는 방향을 저장하는 mask 변수 -> 각 CCTV마다 2비트 1 : << (2 * cctv개수)
		for (int mask = 0; mask < (1 << 2 * (cctvList.size())); mask++) {
			// 5. 조합이 정해졌으면 tempArr에 기존 배열 값 복사
			tempArr = new int[n][m];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					tempArr[i][j] = arr[i][j];

			// 6. 복사해놓은 배열을 사용해서 각 CCTV마다 방향을 감시했다고 체크
			int temp = mask;
			for (int i = 0; i < cctvList.size(); i++) {
				// 6-1. mask를 temp에 복사하고 그거를 4로 나눈 나머지가 현재 보고 있는 방향
				int dir = temp % 4;
				// 6-2. temp를 4로 나눠놓기
				temp /= 4;

				// 6-3. 현재 cctv의 좌표 가져오기
				int curX = cctvList.get(i).x;
				int curY = cctvList.get(i).y;

				// 6-4. 각 cctv의 번호를 기준으로 방향탐색 (1 ~ 5)
				// 1 ~ 5: CCTV (1: dir, 2: <dir, dir + 2>, 3: <dir, dir + 1>, 4: <dir, dir + 1,
				// dir + 2>, 5: <dir, dir + 1, dir + 2, dir + 3>)
				switch (arr[curX][curY]) {
				case 1:
					watch(curX, curY, dir);
					break;
				case 2:
					watch(curX, curY, dir);
					watch(curX, curY, (dir + 2));
					break;
				case 3:
					watch(curX, curY, dir);
					watch(curX, curY, dir + 1);
					break;
				case 4:
					watch(curX, curY, dir);
					watch(curX, curY, (dir + 1));
					watch(curX, curY, (dir + 2));
					break;
				case 5:
					watch(curX, curY, dir);
					watch(curX, curY, dir + 1);
					watch(curX, curY, (dir + 2));
					watch(curX, curY, (dir + 3));
					break;
				}
			}
			
			int curWatch = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(tempArr[i][j] == 0)
						curWatch++;
				}
			}
			minWatch = Math.min(curWatch, minWatch);
		}
		System.out.println(minWatch);
	}

	public static void watch(int x, int y, int dir) {
		// dir 범위 확인
		dir %= 4;
		while (true) {
			x += dx[dir];
			y += dy[dir];

			// 격자를 벗어났거나 벽에 부딪히면 retuirn
			if (x < 0 || x >= n || y < 0 || y >= m || arr[x][y] == 6) {
				return;
			}
			// 0이 아니면 다음곳으로 이동
			if (tempArr[x][y] != 0) {
				continue;
			}
			//감시했다는 뜻으로 7마킹
			tempArr[x][y] = 7;
		}
	}
}