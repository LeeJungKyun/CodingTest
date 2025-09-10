import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 * 1. 편의점 개수 n 입력
 * 2. 집 좌표 입력
 * 3. n 줄에 걸친 편의점 좌표
 * 4. 목적지 주소
 * 5. 전부 다 points 에 넣어놓고 0번부터 BFS 해서 n - 1에 도착 하기
 */

public class Main {
	//클래스 선언부
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	static final int DISTANCE = 1000;

	//변수 선언부
	static int testCase;
	static int storeCount;
	static ArrayList<Point> points;
	static int[][] dist;
	static Point start, end;

	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			points = new ArrayList<>();
			//1. 편의점 개수 입력
			storeCount = Integer.parseInt(br.readLine());
			
			//2. 집 좌표 입력
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			start = new Point(x, y);
			points.add(start);
			
			//3. n줄에 걸쳐서 편의점 좌표ㅑ
			for(int i = 0; i < storeCount; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				points.add(new Point(x, y));
			}
			
			//4. 펜타포트 락 페스티벌 주소 입력
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			end = new Point(x, y);
			points.add(end);
		
			ArrayDeque<Point> queue = new ArrayDeque<>();
			boolean[] visited = new boolean[points.size()];
			
			//집에서 시작해서 n - 1 번 인덱스까지 갈건데 각 포인트마다 현재부터의 거리가 DISTANCE 이하이면 ㄱㄱ
			queue.add(points.get(0));
			visited[0] = true;
			boolean canReach = false;
			
			while(!queue.isEmpty()) {
				Point cur = queue.poll();
				
				//현재가 락페스티벌이면 끝내기
				if(cur.x == end.x && cur.y == end.y) {
					canReach = true;
					break;
				}
				
				//1번 인덱스부터 points.size() - 2번 인덱스까지 탐색하면서 방문안했고, 갈수 있으면 ㄱㄱ
				for(int selectedPointIndex = 1; selectedPointIndex < points.size(); selectedPointIndex++) {
					if(!visited[selectedPointIndex] && calculateDistance(cur, points.get(selectedPointIndex)) <= DISTANCE) {
						visited[selectedPointIndex] = true;
						queue.add(points.get(selectedPointIndex));
					}
				}
			}
			
			sb.append(canReach ? "happy" : "sad").append('\n');
		}
		System.out.println(sb);
	}
	
	public static int calculateDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}