import java.io.*;
import java.util.*;

public class Main {
	static int n, result = 0;
	static boolean[] visited;
	static int[] arr;
	static ArrayDeque<Integer> deque = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		visited = new boolean[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = Integer.parseInt(br.readLine());
		
		deque.add(start - 1);
		
		while(!deque.isEmpty()) {
			int cur = deque.pollFirst();
			visited[cur] = true;
			int plusNext = cur + arr[cur];
			int minusNext = cur - arr[cur];
			
			if(plusNext >= 0 && plusNext < n && !visited[plusNext]) {
				deque.addLast(plusNext);
			}
			if(minusNext >= 0 && minusNext < n && !visited[minusNext]) {
				deque.addLast(minusNext);
			}
		}
		
		
		for(int i = 0; i < n; i++) {
			if(visited[i])
				result++;
		}
		
		System.out.println(result);
	}
}
