import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] distance, cost;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		distance = new int[n];
		cost = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			distance[i] = d;
			cost[i] = c;
		}
		
		int ans = 0;
		
		for(int i = 0; i < n; i++) {
			int curDist = distance[i];
			int curCost = cost[i];
			boolean chk = true;
			for(int j = 0; j < n; j++) {
				if(i == j) continue;
				if(curCost > cost[j] && curDist >= distance[j]) {
					chk = false;
					break;
				}
				
				if(curDist > distance[j] && curCost >= cost[j]) {
					chk = false;
					break;
				}
			}
			if(chk) ans++;
		}
		System.out.println(ans);
	}
}