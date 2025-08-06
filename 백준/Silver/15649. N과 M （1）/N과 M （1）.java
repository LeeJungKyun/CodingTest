import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] arr, result;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1];
		visited = new boolean[n + 1];
		for(int i = 1; i <= n; i++) {
			arr[i] = i;
		}
		
		result = new int[m];
		
		getSequence(0);
		
		System.out.println(sb);
	}
	
	public static void getSequence(int depth) {
		if(depth == m) {
			for(int num : result)
				sb.append(num).append(" ");
			sb.append('\n');
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[depth] = i;
				getSequence(depth + 1);
				visited[i] = false;
			}
		}
	}
}
