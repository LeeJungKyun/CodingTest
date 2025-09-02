import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 1 ~ N 까지의 나무 그루터기
 * M개의 오솔길
 * 여우와 늑대의 초기 위치는 1번 그루터기
 * 
 * 늑대는 오솔길 하나는 두배로, 하나는 절반의 속도로 달려가고 여우는 그냥 감 -> 따라서 다를 수 있음
 * 여우가 더 빨리 도착하는 그루터기의 개수를 출력
 * 
 * 
 * 1. n, m 입력(2 ~ N ~ 4000 / 1 ~ M ~ 100,000)
 * 2. M줄에 걸쳐서 간선 정보 입력 (start, to, cost)
 * 3. distFox 초기화 후 여우 이동 시간 확인
 * 4. distWolf 초기화 후 늑대 이동시간 확인
 *
 */
public class Main {
	//클래스
	static class Node implements Comparable<Node> {
		int to, weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}


		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static class WolfNode implements Comparable<WolfNode> {
		int to, state;
		int weight;
		
		public WolfNode(int to, int weight, int state) {
			this.to = to;
			this.weight = weight;
			this.state = state;
		}
		@Override
		public int compareTo(WolfNode o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	//상수
	static final int INF = Integer.MAX_VALUE;
	//변수
	static int n, m;
	static ArrayList<Node>[] graph;
	static int[] distFox;
	static int[][] distWolf;
	//입출력
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n, m 입력(2 ~ N ~ 4000 / 1 ~ M ~ 100,000)
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		graph = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++)
			graph[i] = new ArrayList<>();
		
		//2. M줄에 걸쳐서 간선 정보 입력 (start, to, cost)
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//양방향 연결 (2로 나눠도 그냥 정수이도록 곱해놓기)
			graph[start].add(new Node(to, cost * 2));
			graph[to].add(new Node(start, cost * 2));
		}
		
		//3. distFox 초기화 후 여우 이동 시간 확인
		distFox = new int[n + 1];
		Arrays.fill(distFox, INF);
		dijkstraFox();
		
		//4. distWolf 초기화 후 늑대 이동시간 확인
		dijkstraWolf();
		
		int result = 0;
		for(int i = 2; i <= n; i++) {
			int foxArrival = distFox[i];
			int wolfArrival = Math.min(distWolf[i][0], distWolf[i][1]);
			
			if(foxArrival < wolfArrival)
				result++;
		}
		
		System.out.println(result);

	}
	
	public static void dijkstraFox() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		//시작점은 1에서 시작
		distFox[1] = 0;
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int now = cur.to;
			
			if(distFox[now] < cur.weight) continue;
			
			for(Node next : graph[now]) {
				int nextNode = next.to;
				int newCost = distFox[now] + next.weight;
				
				if(distFox[nextNode] > newCost) {
					distFox[nextNode] = newCost;
					pq.add(new Node(nextNode, newCost));
				}
			}
		}
	}
	
	public static void dijkstraWolf() {
	    PriorityQueue<WolfNode> pq = new PriorityQueue<>();
	    distWolf = new int[n + 1][2];
	    for (int i = 1; i <= n; i++) {
	        Arrays.fill(distWolf[i], INF);
	    }
	    
	    distWolf[1][0] = 0; // 시작은 빠른 상태로 시작
	    pq.add(new WolfNode(1, 0, 0));

	    while (!pq.isEmpty()) {
	        WolfNode cur = pq.poll();
	        int now = cur.to;
	        int state = cur.state;

	        if (distWolf[now][state] < cur.weight) continue;

	        for (Node next : graph[now]) {
	            int nextNode = next.to;
	            int nextState = 1 - state;

	            int newCost = (state == 0)
	                    ? distWolf[now][state] + next.weight / 2
	                    : distWolf[now][state] + next.weight * 2;

	            if (distWolf[nextNode][nextState] > newCost) {
	                distWolf[nextNode][nextState] = newCost;
	                pq.add(new WolfNode(nextNode, newCost, nextState));
	            }
	        }
	    }
	}

	
}