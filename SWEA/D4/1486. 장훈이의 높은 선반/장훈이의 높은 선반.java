import java.io.*;
import java.util.*;

public class Solution {
	static int testCase, n, b;
	static int[] height;
	static int minRequiredHeight;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			minRequiredHeight = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			height = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				height[i] = Integer.parseInt(st.nextToken());
			
			for(int mask = 1; mask < (1 << n); mask++) {
				int currentHeightSum = 0;
				for(int i = 0; i < n; i++) {
					if((mask & (1 << i)) != 0) {
						currentHeightSum += height[i];
					}
				}
				if(currentHeightSum < b) continue;
				minRequiredHeight = Math.min(minRequiredHeight, currentHeightSum);
			}
			sb.append('#').append(tc).append(' ').append(minRequiredHeight - b).append('\n');
		}
		System.out.println(sb);
	}
}
