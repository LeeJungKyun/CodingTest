import java.io.*;
import java.util.*;

/**
 * @author 이정균 (N + 1) x M 짜리의 격자판이 존재 N + 1 의 모든 칸에는 성이 있다 ( 3 <= n, m <= 15)
 *         궁수는 성에 배치 -> 3명 배치 공격 방식 : 맨해튼 거리가 D이하인 적 중에서 가장 가까운적 -> 여러명이면 가장 좌측 적
 * 
 */
public class Main {
	static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Point)) return false;
			Point p = (Point) o;
			return x == p.x && y == p.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		
	}
	static int n, m, d;
	static int[][] map;
	static int[] dx = {0, -1, 0};
	static int[] dy = {-1, 0, 1};
	static int[] archerPositions = new int[3]; // 궁수 3명의 위치
	static List<int[]> archerCombination = new ArrayList<>();
	static int maxKillCount = Integer.MIN_VALUE;

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 궁수의 위치를 정하고 시뮬 시작
		makeCombination(0, 0);

		for (int[] positions : archerCombination) {
			int[][] copiedMap = copyMap(map);
			int killCount = castleDefense(copiedMap, positions);
			maxKillCount = Math.max(maxKillCount, killCount);
		}
		
		System.out.println(maxKillCount);
	}

	public static int castleDefense(int[][] map, int[] archerPosition) {
		int killCount = 0;
		while (enemyExist(map)) {
			 // 이번 턴에 궁수들이 공격할 적 저장
			Set<Point> targets = new HashSet<>();

			// 1. 궁수별로 공격 대상 선택
			for (int archer : archerPosition) {
				Point target = findTarget(map, archer);
				if (target != null) {
					targets.add(target); // HashSet이므로 중복 제거됨
				}
			}

			// 2. 적 제거
			for (Point p : targets) {
				if (map[p.x][p.y] == 1) {
					map[p.x][p.y] = 0;
					killCount++;
				}
			}

			// 3. 적 이동
			moveEnemies(map);
		}
		return killCount;
	}
	
	public static Point findTarget(int[][] map, int archerCol) {
		boolean[][] visited = new boolean[n][m];
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(n - 1, archerCol)); // 궁수는 n행에 있으므로, 바로 위에서 탐색 시작
		visited[n - 1][archerCol] = true;

		int dist = 1;

		while (!q.isEmpty() && dist <= d) {
			int size = q.size();
			List<Point> candidates = new ArrayList<>();

			for (int i = 0; i < size; i++) {
				Point cur = q.poll();

				if (map[cur.x][cur.y] == 1) {
					candidates.add(cur);
				}

				for (int dir = 0; dir < 3; dir++) {
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];

					if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny));
					}
				}
			}

			if (!candidates.isEmpty()) {
				candidates.sort((a, b) -> Integer.compare(a.y, b.y));
				return candidates.get(0);
			}

			dist++;
		}

		return null;
	}

	public static void moveEnemies(int[][] map) {
		for (int i = n - 1; i > 0; i--) {
			map[i] = Arrays.copyOf(map[i - 1], m);
		}
		Arrays.fill(map[0], 0);
	}

	public static void makeCombination(int start, int depth) {
		if (depth == 3) {
			// 조합 하나 완성됐으니 복사해서 리스트에 저장
			archerCombination.add(archerPositions.clone());
			return;
		}

		for (int i = start; i < m; i++) {
			archerPositions[depth] = i;
			makeCombination(i + 1, depth + 1);
		}
	}

	public static int[][] copyMap(int[][] map) {
		int[][] tempArr = new int[n + 1][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tempArr[i][j] = map[i][j];
			}
		}
		return tempArr;
	}

	public static boolean enemyExist(int[][] map) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1)
					return true;
			}
		}
		return false;
	}
}