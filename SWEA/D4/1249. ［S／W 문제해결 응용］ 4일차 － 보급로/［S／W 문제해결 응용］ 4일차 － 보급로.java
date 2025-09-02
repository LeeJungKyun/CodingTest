import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 출발지: 좌상단
 * 도착지: 우하단
 * 파여진 도로의 깊이가 주어지고, 현재 위치한 칸의 도로를 복구해야함
 * 0은 복구 작업이 불필요한 곳, 1은 복구 작업이 필요한 곳
 * 
 * 1. SIZE 입력
 * 2. 배열 입력
 * 3. dist배열 INF로 초기화
 * 4. dijkstra알고리즘 시작
 * 	4-1. PriorityQueue 선언해서 시작점(0, 0) 넣고 dist초기화
 * 	4-2. pq가 빌때까지 poll
 * 	4-3. 현재 뽑은 노드좌표의 비용이 해당 좌표를 방문하는 비용보다 크면 스킵
 *	4-4. 4방향을 탐색하면서 범위 안에 있는 지 확인
 *	4-5. 새로운 비용은 현재 코스트에 arr값을 더한 비용
 *	4-6. 새로운 비용이 dist 값보다 작으면 갱신하고 pq에 추가
 */
public class Solution {
	static class Node implements Comparable<Node>{
		int x, y;
		int cost;
		
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	static int testCase;
	static int SIZE;
	static final int INF = Integer.MAX_VALUE;
	static int minCost = INF;
	static int[][] arr, dist;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//1. SIZE 입력
			SIZE = Integer.parseInt(br.readLine());
			
			//2. 배열 입력
			arr = new int[SIZE][SIZE];
			for(int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for(int j = 0; j < SIZE; j++) {
					arr[i][j] = line.charAt(j) - '0';
				}
			}
			
			//3. dist 배열 INF로 초기화
			dist = new int[SIZE][SIZE];
			for(int i = 0; i < SIZE; i++)
				Arrays.fill(dist[i], INF);
			
			findMinCostByDijkstra();
			
			sb.append('#').append(tc).append(' ').append(dist[SIZE - 1][SIZE - 1]).append('\n');
		}
		System.out.println(sb);
	}
	
	
	public static void findMinCostByDijkstra() {
		//4-1. PriorityQueue 선언해서 시작점(0, 0) 넣고 dist초기화
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, 0));
		//dist초기화
		dist[0][0] = 0;
		
		//4-2. pq가 빌때까지 poll
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curCost = cur.cost;
			
			//4-3. 현재 뽑은 노드좌표의 비용이 해당 좌표를 방문하는 비용보다 크면 스킵
			if(dist[curX][curY] < curCost) continue;
			
			//4-4. 4방향을 탐색하면서 범위 안에 있는 지 확인
			for(int dir = 0; dir < 4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				
				if(nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) continue;
				
				//4-5. 새로운 비용은 현재 코스트에 arr값을 더한 비용
				int newCost = curCost + arr[nx][ny];
				
				//4-6. 새로운 비용이 dist 값보다 작으면 갱신하고 pq에 추가
				if(newCost < dist[nx][ny]) {
					dist[nx][ny] = newCost;
					pq.add(new Node(nx, ny, newCost));
				}
			}
			
			
		}
	}
}