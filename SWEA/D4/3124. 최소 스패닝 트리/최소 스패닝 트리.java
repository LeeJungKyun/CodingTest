import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 1. 테스트케이스 입력
 * 2. 정점의 개수 v, 간선의 개수 e 입력
 * 3. e 개의 줄에 a b c 입력 a 와 b를 연결하는게 c비용
 * 4. 간선 정렬
 * 5. 필요 변수 초기화
 * 6. 간선 순회하면서 안합쳐져 있으면 합치기
 * 6. 
 *
 */
public class Solution {
	static class Edge implements Comparable<Edge> {
		int start, dest, weight;

		public Edge(int start, int dest, int weight) {
			super();
			this.start = start;
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
	static int[] parent;
	static ArrayList<Edge> graph;
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
			
			//3. e개의 줄에 a b c 입력
			graph = new ArrayList<>();
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				graph.add(new Edge(start, end, weight));
			}
			
			//4. 간선 정렬
			Collections.sort(graph);
			
			//5. 필요 변수 초기화
			parent = new int[v + 1];
			for(int i = 1; i <= v; i++)
				parent[i] = i;
			result = 0;
			
			//6. 간선 순회하면서 안합쳐져 있으면 합치기
			int edgeCount = 0;
			for(Edge edge : graph) {
				if(find(edge.start) != find(edge.dest)) {
					result += edge.weight;
					edgeCount++;
					union(edge.start, edge.dest);
					if(edgeCount == v - 1)
						break;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int find(int x) {
		if(x == parent[x])
			return x;
		else return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y)
			parent[y] = x;
	}
}