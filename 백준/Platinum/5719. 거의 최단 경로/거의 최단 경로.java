import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 *         일단 다익스트라로 최단경로 찾고 해당 최단경로를 다 저장해둠.그리고 저장해둔 최단경로들을 역으로 탐색해나가면서 해당
 *         경로들을 다 INF로 초기화하거나 그냥 연결을 끊어버림 그리고 다시 다익스트라 돌리기
 *
 */
public class Main {
	static class Node implements Comparable<Node> {
		int from, to, weight;

		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	static final int INF = Integer.MAX_VALUE;
	static int n, m;
	static int s, d;
	static int[] dist;
	static ArrayList<Node>[] graph;
	static ArrayList<Integer>[] prev;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		while (n != 0 && m != 0) {

			// 거리 저장 배열 -> 유향 그래프
			dist = new int[n];
			Arrays.fill(dist, INF);

			// 시작점, 도착점
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());

			// 도로 정보 주어짐
			graph = new ArrayList[n];
			for (int i = 0; i < n; i++)
				graph[i] = new ArrayList<>();

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				// 도로 연결
				graph[u].add(new Node(u, v, p));
			}

			findMinDistanceDijkstra();
			removePath(d);
			Arrays.fill(dist, INF);
			findMinDistanceDijkstra();

			// 출력에 추가
			sb.append(dist[d] == INF ? -1 : dist[d]).append('\n');
			// 다시 시작 변수 초기화
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}

		System.out.println(sb);
	}

	public static void findMinDistanceDijkstra() {
		dist[s] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(s, s, 0));

		prev = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			prev[i] = new ArrayList<>();
		}

		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int curWeight = curNode.weight;
			int now = curNode.to;

			// 현재뽑은 노드에 대해서 다음에 갈 수 있는 곳들을 확인
			for (Node next : graph[now]) {
				int nextNode = next.to;
				int newCost = dist[now] + next.weight;

				if (dist[nextNode] > newCost) {
					dist[nextNode] = newCost;
					pq.add(new Node(now, nextNode, newCost));

					// 최단거리가 바뀌었으니 이전 경로 최신화
					prev[nextNode].clear();
					prev[nextNode].add(now);
				} else if (dist[nextNode] == newCost) {
					prev[nextNode].add(now);
				}
			}
		}
	}

	public static void removePath(int dest) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(dest);

		boolean[][] visited = new boolean[n][n];

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int prevNode : prev[now]) {
				// 이미 제거한 간선은 중복 제거 방지
				if (visited[prevNode][now])
					continue;
				visited[prevNode][now] = true;

				// prevNode -> now 간선 제거
				graph[prevNode].removeIf(e -> e.to == now);

				queue.add(prevNode);
			}
		}
	}

}