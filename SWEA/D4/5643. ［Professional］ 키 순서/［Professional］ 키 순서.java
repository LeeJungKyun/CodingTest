import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Solution {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int n, m;
	static boolean[][] reachable;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//학생 수 n, 비교 횟수  m
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			
			reachable = new boolean[n + 1][n + 1];
			
			//키 순서 입력받기 (a < b)
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				reachable[a][b] = true;
			}
			
			//나한테 들어오는 간선의 합 + 나가는 간선의 합 == 학생수 - 1이라면 자신의 키가 몇번째인지 알 수 있음
			//도달할 수 있는지 아닌지 확인하기 위한 reachable 을 플로이드워셜로 탐색
			for (int k = 1; k <= n; k++) {
			    for (int i = 1; i <= n; i++) {
			        for (int j = 1; j <= n; j++) {
			            if (reachable[i][k] && reachable[k][j]) {
			                reachable[i][j] = true;
			            }
			        }
			    }
			}
			
			int result = 0;
			for (int i = 1; i <= n; i++) {
			    int count = 0;
			    for (int j = 1; j <= n; j++) {
			        if (reachable[i][j] || reachable[j][i]) {
			            count++;
			        }
			    }
			    if (count == n - 1) result++;
			}
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		
		System.out.println(sb);
	}
}