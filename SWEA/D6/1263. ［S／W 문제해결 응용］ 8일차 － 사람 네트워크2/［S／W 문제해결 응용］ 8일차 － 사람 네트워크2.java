import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 플로이드 워셜
 */

public class Solution {
	static final int INF = 987654321;

	static int testCase;
	static int peopleCount;
	static int[][] dist;

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			
			peopleCount = Integer.parseInt(st.nextToken());
			dist = new int[peopleCount][peopleCount];
			
			// dist 배열 초기화
			for(int i = 0; i < peopleCount; i++) {
				for(int j = 0; j < peopleCount; j++) {
					int connected = Integer.parseInt(st.nextToken());
					
					if (i == j) {
						dist[i][j] = 0;
					} else if (connected == 1) {
						dist[i][j] = 1;
					} else {
						dist[i][j] = INF;
					}
				}
			}
			
			// 플로이드-워셜 알고리즘
			for (int k = 0; k < peopleCount; k++) {
				for (int i = 0; i < peopleCount; i++) {
					for (int j = 0; j < peopleCount; j++) {
						if (dist[i][k] != INF && dist[k][j] != INF) {
							dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
						}
					}
				}
			}
			
			int minSum = INF;
			
			// 각 노드별 최단 거리 합 계산
			for(int i = 0; i < peopleCount; i++) {
				int sum = 0;
				for(int j = 0; j < peopleCount; j++) {
					sum += dist[i][j];
				}
				minSum = Math.min(minSum, sum);
			}
			
			sb.append('#').append(tc).append(' ').append(minSum).append('\n');
		}
		
		System.out.println(sb);
	}
}