import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 * 이 코드는 프림(Prim) 알고리즘을 사용하여 최소 스패닝 트리(MST)의 총 비용을 계산합니다.
 * 그래프는 인접 리스트(Adjacency List) 형태로 표현됩니다.
 *
 * 1. 테스트케이스 입력
 * 2. 정점의 개수 v, 간선의 개수 e 입력
 * 3. e개의 줄에 a, b, c(시작, 끝, 가중치)를 입력받아 인접 리스트 초기화
 * 4. 필요한 변수(visited 배열, PriorityQueue) 초기화
 * 5. 프림 알고리즘 실행
 * 6. 결과 출력
 *
 */
public class Solution {
	static class Edge implements Comparable<Edge> {
		int dest, weight;

		public Edge(int dest, int weight) {
			super();
			this.dest = dest;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int testCase;
	static int v, e;
	static long result;
	static ArrayList<Edge>[] adjList; // 인접 리스트 선언
	static boolean[] visited;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 정점의 개수 v, 간선의 개수 e 입력
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			// 인접 리스트 초기화
			adjList = new ArrayList[v + 1];
			for (int i = 1; i <= v; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			//3. e개의 줄에 a b c 입력하면서 인접 리스트 초기화
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				// 양방향 그래프이므로 양쪽에 모두 추가
				adjList[start].add(new Edge(end, weight));
				adjList[end].add(new Edge(start, weight));
			}
			
			
			//4. 필요 변수 초기화
			visited = new boolean[v + 1];
			
			//5. 프림 알고리즘
			result = prim();
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	public static long prim() {
		long minCost = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		int count = 0;
		
		//1번 노드부터 시작, 가중치는 0으로 시작
		pq.offer(new Edge(1, 0));
		
		while(!pq.isEmpty()) {
			Edge curEdge = pq.poll();
			int curDest = curEdge.dest;
			int curWeight = curEdge.weight;
			
			// 이미 방문한 노드면 건너뛰기
			if(visited[curDest])
				continue;
			
			// 현재 노드를 MST에 포함시키고 비용 추가
			visited[curDest] = true;
			minCost += curWeight;
			count++;
			
			// 모든 정점(V)이 연결되면 종료
			if(count == v)
				break;
				
			// 현재 정점에 연결된 모든 간선을 우선순위 큐에 추가
			for(Edge nextEdge : adjList[curDest]) {
				if(!visited[nextEdge.dest]) {
					pq.offer(nextEdge);
				}
			}
		}
		return minCost;
	}
}
