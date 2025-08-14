/**
 * @author 이정균
 * N x N 개의 cell로 구성된 멕시노스
 * 1개의 cell -> Core 또는 전선
 * 가장자리에는 전원이 흐름
 * 전선으로 최대한 많은 Core에 전원을 연결
 * 가능한 경우의 수가 많으면 전선 길이의 합이 최소가 되는 값
 *
 * 1. testCase 입력
 * 2. testCase별 필요 변수 초기화
 * 3. N입력
 * 4. processor 배열 입력 (0 빈cell, 1 core) -> coreList 저장
 * 5. 0번인덱스부터 connectLine함수 백트래킹 실행
 * 	5-1. 인덱스가 끝까지 돌았다면 maxConnectedCount, minLineSum 갱신
 * 	5-2. 현재 Core를 가져와서 연결되었는지 확인해서 연결되어있다면 다음 코어인덱스 탐색
 *  5-3. 연결되어있지 않다면 4방향 각각 연결가능한지 확인해보고 다음 인덱스 탐색
 */
import java.io.*;
import java.util.*;

public class Solution {
	static class Core {
		int x, y;
		boolean isConnected;

		public Core(int x, int y, boolean isConnected) {
			this.x = x;
			this.y = y;
			this.isConnected = isConnected;
		}
	}
	static int testCase, N;
	static int minLineSum, maxConnectedCount;
	static int[][] processor;
	static ArrayList<Core> coreList;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 1. testCase 입력
		testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			// 2. testCase별 필요 변수 초기화
			coreList = new ArrayList<>();
			minLineSum = Integer.MAX_VALUE;
			maxConnectedCount = 0;
			// 3. N입력
			N = Integer.parseInt(br.readLine());

			// 4. processor 배열 입력 (0 빈cell, 1 core) -> coreList 저장
			processor = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					processor[i][j] = Integer.parseInt(st.nextToken());
					// core라면 coreList에 저장
					if (processor[i][j] == 1) {
						if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
							coreList.add(new Core(i, j, true));
						} else {
							coreList.add(new Core(i, j, false));
						}
					}
				}
			}
			connectLine(0, 0, 0);
			
			sb.append('#').append(tc).append(' ').append(minLineSum).append('\n');
		}
		System.out.println(sb);
	}

	public static void connectLine(int coreIndex, int connectedCount, int lineSum) {
		// 5-1. 인덱스가 끝까지 돌았다면 maxConnectedCount, minLineSum 갱신
		if (coreIndex == coreList.size()) {
			if (connectedCount > maxConnectedCount) {
				maxConnectedCount = connectedCount;
				minLineSum = lineSum;
			} else if (connectedCount == maxConnectedCount) {
				minLineSum = Math.min(minLineSum, lineSum);
			}
			return;
		}

		// 5-2. 현재 Core를 가져와서 연결되었는지 확인해서 연결되어있다면 다음 코어인덱스 탐색
		Core core = coreList.get(coreIndex);
		if (core.isConnected) {
			connectLine(coreIndex + 1, connectedCount + 1, lineSum);
			return;
		}
		// 5-3. 연결되어있지 않다면 4방향 각각 연결가능한지 확인해보고 다음 인덱스 탐색
		for (int dir = 0; dir < 4; dir++) {
			if (canConnect(core.x, core.y, dir)) {
				//전선 설치
				int length = drawLine(core.x, core.y, dir, 2);
				//다음 인덱스 확인
				connectLine(coreIndex + 1, connectedCount + 1, lineSum + length);
				//전선 제거
				drawLine(core.x, core.y, dir, 0);
			}
		}
		
		// 5-4. 그냥 연결 안하고 해보기
		connectLine(coreIndex + 1, connectedCount, lineSum);
	}

	//연결 가능한지 확인
	public static boolean canConnect(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		//선택된 방향으로 끝까지 갔을때 코어가 없으면 true, 있으면 false return
		while (0 <= nx && nx < N && 0 <= ny && ny < N) {
			if (processor[nx][ny] != 0)
				return false;
			nx += dx[dir];
			ny += dy[dir];
		}
		return true;
	}

	//전선을 깔고, 지우는 함수
	public static int drawLine(int x, int y, int dir, int value) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		int drawLength = 0;
		while (0 <= nx && nx < N && 0 <= ny && ny < N) {
			processor[nx][ny] = value;
			drawLength++;
			nx += dx[dir];
			ny += dy[dir];
		}
		return drawLength;
	}
}
