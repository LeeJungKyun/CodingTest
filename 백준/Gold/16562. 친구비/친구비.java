import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static int[] cost;
	static int[] parents;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k= Integer.parseInt(st.nextToken());
		
		cost = new int[n + 1];
		parents = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		parents = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if(v > w) union(w, v);
			else union(w, v);
		}
		
		long count = 0;
		boolean[] check = new boolean[n + 1];
		for(int i = 1; i <= n; i++) {
			int idx = find(i);
			
			if(!check[idx]) {
				count += cost[idx];
				check[idx] = true;
			}
			check[i] = true;
		}
		
		System.out.println(count > k ? "Oh no" : count);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(cost[x] > cost[y])
			parents[x] = y;
		else parents[y]= x;
	}
	
	public static int find(int x) {
		if(x == parents[x])
			return x;
		else return parents[x] = find(parents[x]);
	}
}