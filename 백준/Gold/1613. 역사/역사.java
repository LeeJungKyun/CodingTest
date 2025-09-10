import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 앞이면 -1, 뒤면 1, 모르겠으면 0
 * 1-based distance 배열을 사용해서 플로이드 워셜
 *
 */
public class Main {
	static int n, k, s;
	static int[][] distance;
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		distance = new int[n + 1][n + 1];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			distance[a][b] = -1; // a가 b보다 먼저
			distance[b][a] = 1; // b가 a보다 나중
		}

		// 플로이드 워셜
		for (int m = 1; m <= n; m++) { // m이 경유 노드
			for (int start = 1; start <= n; start++) {
				for (int end = 1; end <= n; end++) {
					// start -> m -> end 관계가 성립하면
					if (distance[start][m] == -1 && distance[m][end] == -1) {
						distance[start][end] = -1; // start가 end보다 먼저
						distance[end][start] = 1; // end가 start보다 나중
					}
				}
			}
		}

		s = Integer.parseInt(br.readLine());
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(distance[a][b]).append("\n");
		}

		System.out.println(sb);
	}
}