import java.io.*;
import java.util.*;

public class Solution {
	static final int testCase = 10;
	static int length, start;
	static int maxDepth, maxNode;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			length = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			graph = new ArrayList[101];
			visited = new boolean[101];

			for (int i = 1; i <= 100; i++) {
				graph[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < length / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}
			visited[start] = true;
			findLatestConnection(start);
		    sb.append("#").append(tc).append(" ").append(maxNode).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void findLatestConnection(int start) {
	    Queue<int[]> queue = new LinkedList<>();
	    visited = new boolean[101];
	    maxDepth = 0;
	    maxNode = 0;

	    queue.offer(new int[] {start, 0});
	    visited[start] = true;

	    while (!queue.isEmpty()) {
	        int[] curr = queue.poll();
	        int currNode = curr[0];
	        int depth = curr[1];
	        
	        if (depth > maxDepth) {
	            maxDepth = depth;
	            maxNode = currNode;
	        } else if (depth == maxDepth && currNode > maxNode) {
	            maxNode = currNode;
	        }

	        for (int next : graph[currNode]) {
	            if (!visited[next]) {
	                visited[next] = true;
	                queue.offer(new int[] {next, depth + 1});
	            }
	        }
	    }
	}
}
