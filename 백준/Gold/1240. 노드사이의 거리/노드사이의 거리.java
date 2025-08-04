import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int dest;
		int weight;
		
		public Node(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}
	static int n, m, result;
	static List<Node>[] graph;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];
		for(int i = 1; i<= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, weight));
			graph[to].add(new Node(from, weight));
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			dfs(end, -1, start, 0);
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int to, int parent, int from, int sum) {
		if(to == from)
			result = sum;
		for(Node next : graph[to]) {
			if(next.dest != parent) {
				dfs(next.dest, to, from, sum + next.weight);
			}
		}
	}
}
