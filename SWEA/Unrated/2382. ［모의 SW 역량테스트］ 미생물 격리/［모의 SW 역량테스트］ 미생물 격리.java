import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N x N크기의 정사각형
 * 군집의 개수 K, 격리 시간 M
 * * - 약품에 닿으면 반갈나고 방향 반대로
 * - 군집끼리 만나면 둘이 합쳐지고 큰쪽의 이동방향을 따라감
 * * 1. 테스트케이스 입력
 * 2. N, M, K입력
 * 3. k줄에 x, y, 미생물 수, 이동방향 주어짐 -> 이동방향 1빼서 저장
 * 4. 필요 변수 초기화
 * 5. m일동안 미생물 움직이기
 *
 */
public class Solution {
	static class Micro{
		int id;
		int x, y, num, dir;

		public Micro(int id, int x, int y, int num, int dir) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			Point point = (Point) obj;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	
	//0~3 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int testCase;
	static int result;
	static int n, m, k;
	static List<Micro> microList;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. N, M, K입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			microList = new ArrayList<>();
			
			//3. k줄에 x, y, 미생물 수, 이동방향 주어짐 -> 이동방향 1빼서 저장
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				// 1:상(0), 2:하(1), 3:좌(2), 4:우(3)로 변환
				if (dir == 1) dir = 0;
				else if (dir == 2) dir = 1;
				else if (dir == 3) dir = 2;
				else if (dir == 4) dir = 3;
				
				microList.add(new Micro(i, x, y, num, dir));
			}

			//4. M일동안 미생물 움직이기 시작
			while(m-- > 0) {
				moveMicro();
			}
			
			result = 0;
			for (Micro micro : microList) {
				result += micro.num;
			}
			
			//출력
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void moveMicro() {
		// key: 다음 위치, value: 해당 위치로 이동하는 미생물 군집 리스트
		HashMap<Point, List<Micro>> nextPositions = new HashMap<>();

		// 1. 모든 미생물 군집의 다음 위치와 상태를 계산하여 임시 맵에 저장
		for (Micro micro : microList) {
			if (micro.num == 0) continue;

			int nx = micro.x + dx[micro.dir];
			int ny = micro.y + dy[micro.dir];
			int nextNum = micro.num;
			int nextDir = micro.dir;

			// 약품에 닿으면
			if (nx == 0 || nx == n - 1 || ny == 0 || ny == n - 1) {
				nextNum /= 2;
				// 방향 반대로
				if (nextDir == 0) nextDir = 1;
				else if (nextDir == 1) nextDir = 0;
				else if (nextDir == 2) nextDir = 3;
				else if (nextDir == 3) nextDir = 2;
			}

			if (nextNum > 0) {
				Micro nextMicro = new Micro(micro.id, nx, ny, nextNum, nextDir);
				Point p = new Point(nx, ny);
				
				nextPositions.putIfAbsent(p, new ArrayList<>());
				nextPositions.get(p).add(nextMicro);
			}
		}
		
		// 2. 새로운 미생물 리스트 생성 및 충돌 처리
		List<Micro> nextMicroList = new ArrayList<>();
		for (Point p : nextPositions.keySet()) {
			List<Micro> collidingMicros = nextPositions.get(p);
			
			if (collidingMicros.size() == 1) {
				// 충돌 없음
				nextMicroList.add(collidingMicros.get(0));
			} else {
				// 충돌 발생
				int mergedNum = 0;
				int maxNum = 0;
				Micro dominantMicro = null;
				
				for (Micro m : collidingMicros) {
					mergedNum += m.num;
					if (m.num > maxNum) {
						maxNum = m.num;
						dominantMicro = m;
					}
				}
				
				// 합쳐진 새로운 군집 생성
				Micro mergedMicro = new Micro(-1, p.x, p.y, mergedNum, dominantMicro.dir);
				nextMicroList.add(mergedMicro);
			}
		}
		
		// 3. 기존 리스트를 새로운 리스트로 교체
		microList = nextMicroList;
	}
}