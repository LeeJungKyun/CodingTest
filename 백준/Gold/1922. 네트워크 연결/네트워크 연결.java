import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 컴퓨터 수 N, 간선 M
 * a b c -> a와 b연결하는데 비용 c
 *
 * 1. n입력
 * 2. m입력
 * 3. parent 배열 초기화
 * 4. m줄에 간선 입력 받으면서 양방향 연결하기
 * 5. 그래프 비용 순 정렬
 * 6. 비용순으로 정렬된 간선을 순회하면서 간선의 start와 dest가 같은 그룹이 아니라면 비용 더하고, union
 */
public class Main {
	static class Edge implements Comparable<Edge> {
		int start, dest, cost;

		public Edge(int start, int dest, int cost) {
			this.start = start;
			this.dest = dest;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static int n, m;
	static int result = 0;
	static ArrayList<Edge> graph;
	static int[] parent;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n입력
		n = Integer.parseInt(br.readLine());
		//2. m입력
		m = Integer.parseInt(br.readLine());
		
		//3. parent 배열 초기화
		parent = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		//4. m줄에 간선 입력받으면서 양방향 연결하기
		graph = new ArrayList<>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.add(new Edge(start, end, cost));
		}
		
		//5. 그래프 비용 순 정렬
		Collections.sort(graph);
		
		//6. 비용순으로 정렬된 간선을 순회하면서 간선의 start와 dest가 같은 그룹이 아니라면 비용 더하고, union
		for (Edge edge : graph) {
            if (find(edge.start) != find(edge.dest)) {
                result += edge.cost;
                union(edge.start, edge.dest);
            }
        }
		
		System.out.println(result);
	}
	
	public static int find(int x) {
		if(x == parent[x]) {
			return x;
		} else return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) {
			parent[y] = x;
		}
	}
}