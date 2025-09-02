import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int x, y, cost;

		public Node(int x, int y, int cost) {
			super();
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
	static int n;
	static int[][] arr;
	static int[][] distance;
	static final int INF = Integer.MAX_VALUE;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = 1;

		while((n = Integer.parseInt(br.readLine())) != 0) {
			arr = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			distance = new int[n][n];
			for(int i = 0; i < n; i++)
				Arrays.fill(distance[i], INF);
			
			dijkstra();
			sb.append("Problem").append(' ').append(testCase).append(':').append(' ').append(distance[n - 1][n - 1]).append('\n');
			testCase++;
		}
		System.out.println(sb);
	}
	
	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, arr[0][0]));
		distance[0][0] = arr[0][0];
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curCost = cur.cost;
			
			if(distance[curX][curY] < curCost) continue;
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				int newCost = curCost + arr[nx][ny];
				
				if(newCost < distance[nx][ny]) {
					distance[nx][ny] = newCost;
					pq.add(new Node(nx, ny, newCost));
				}
			}
		}
	}
}