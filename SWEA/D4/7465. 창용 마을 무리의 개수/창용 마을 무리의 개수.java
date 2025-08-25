import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 1. 테스트케이스 입력
 * 2. N,M입력
 * 3. 필요 변수 초기화
 * 4. M줄에 지인  관계 입력
 * 5. 입력을 받고 union-find 실행
 * 6. HashSet에 대표번호 넣고 set사이즈 출력?
 */
public class Solution {
	static int testCase;
	static int n, m;
	static int[] parent;
	static HashSet<Integer> set;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. 테스트케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. N,M 입력
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			//3. 필요 변수 초기화
			parent = new int[n + 1];
			for(int i = 1; i <= n; i++)
				parent[i] = i;
			set = new HashSet<>(); 
			
			//4. M줄에 지인  관계 입력
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				//5. 입력을 받고 union-find 실행
				union(a, b);
			}
			
			//6. HashSet에 find(i) 넣고 set사이즈 출력
			for(int i = 1; i <= n; i++) {
				set.add(find(i));
			}
			
			sb.append('#').append(tc).append(' ').append(set.size()).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x > y)
			parent[x] = y;
		else parent[y] = x;
	}
	
	public static int find(int x) {
		if(x != parent[x])
			parent[x] = find(parent[x]);
		return parent[x];
	}
}