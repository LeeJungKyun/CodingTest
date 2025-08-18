import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static BufferedReader br;
	static StringTokenizer st;
	static boolean[][] reachable;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		//학생 수 n, 비교 횟수  m
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
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
		
		System.out.println(result);
	}
}
