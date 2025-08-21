import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N x N 정사각형에 디저트 카페
 * 카페들은 대각선 방향으로 탐색 가능
 * 한 카페에서 출발하여 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야한다.
 * 같은 디저트를 또 먹을 순 없음
 * 한 카페에서 시작해서 바로 끝날 순 없음
 * 디저트를 최대한 많이 먹었을 때, 그 때의 디저트 수를 출력 -> 못 먹으면 -1 출력
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
	//우하, 좌하, 좌상, 우상
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static int testCase, n;
	static int[][] arr;
	static boolean[] eatenDessert;
	static boolean[][] visited;
	static int maxEatCount;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			maxEatCount = -1;
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			
			//배열 입력
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			/**
			 * 시작할 지점 찾기 시작 반복 row : 0 ~ n / 2, 시작 col : 1 ~ n - 1
			 * 오른쪽으로 얼마나 갈지 정하기 (1 ~ n / 2)
			 * 밑으로 얼마나 갈지 정하기 (1 ~ n / 2)
			 */
			for(int startRow = 0; startRow < n - 1; startRow++) {
				for(int startCol = 1; startCol < n - 1; startCol++) {
					//가로 이동량 반복
					for(int width = 1; width < n; width++) {
						//그에따른 세로 이동량 반복
						for(int height = 1; height < n; height++) {
							//조합 정해짐(startRow, startCol)에서 시작해서 가로로 width만큼, 세로로 height만큼
							eatenDessert = new boolean[101];
							
							int curCount = 0;

							Point cur = new Point(startRow, startCol);
							eatenDessert[arr[cur.x][cur.y]] = true;
							boolean isValidTour = true;
							for(int dir = 0; dir < 4; dir++) {
								//세로 이동일 경우
								if(dir % 2 == 1){
									for(int i = 0; i < height; i++) {
										int nx = cur.x + dx[dir];
										int ny = cur.y + dy[dir];
										if (isValid(nx, ny) && (!eatenDessert[arr[nx][ny]] || (nx == startRow && ny == startCol))) {
											eatenDessert[arr[nx][ny]] = true;
											cur.x = nx; cur.y = ny;
											curCount++;
										} else {
											isValidTour = false;
											break;
										}
									}
								}
								//가로 이동일 경우
								else {
									for(int i = 0; i < width; i++) {
										int nx = cur.x + dx[dir];
										int ny = cur.y + dy[dir];
										if (isValid(nx, ny) && (!eatenDessert[arr[nx][ny]] || (nx == startRow && ny == startCol))) {
											eatenDessert[arr[nx][ny]] = true;
											cur.x = nx; cur.y = ny;
											curCount++;
										} else {
											isValidTour = false;
											break;
										}
									}
								}
							}
							//조합 확인 끝
							if (isValidTour && cur.x == startRow && cur.y == startCol) {
							    maxEatCount = Math.max(maxEatCount, curCount);
							}
							
						}
					}
				}
			}	
			sb.append('#').append(tc).append(' ').append(maxEatCount).append('\n');
		}
		System.out.println(sb);
	}
	
	public static boolean isValid(int x, int y) {
		return (0 <= x && x < n && 0 <= y && y < n);
	}
}