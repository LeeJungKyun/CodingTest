import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 

 * 1. 테스트 케이스 입력
 * 2. n, m입력
 * 3. parent집합 초기화
 * 4. m줄에 각각 연산 입력받기
 * 	<1> 0 a b : 합집합 연산
 * 		4-1-1. union(a, b)
 * 		4-1-2. find(a)와 find(b) 중 작은쪽으로 합치기
 * 	<2> 1 a b : 같은 집합인지 확인 연산
 * 		4-2-1. find(a,b)
 * 
 *
 */
public class Solution {
	static int testCase;
	static int n, m;
	static int[] parent;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append('#').append(tc).append(' ');
			//2. n, m입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			//3. parent집합 초기화
			parent = new int[n + 1];
			for(int i = 1; i <= n; i++)
				parent[i] = i;
			
			//4. m줄에 각각 연산 입력받기
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int operator = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// <1> 0 a b : 합집합 연산
				if(operator == 0) {
					//4-1-1. union(a, b)
					union(a, b);
				}
				// <2> 1 a b : 같은 집합인지 확인 연산
				else {
					sb.append(find(a) == find(b) ? 1 : 0);
				}
			}
			
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	
	//4-1-2. find(a)와 find(b) 중 작은쪽으로 합치기
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x > y) {
			parent[x] = y;
		} else parent[y] = x;
	}

	
	public static int find(int x) {
		if(x != parent[x])
			parent[x] = find(parent[x]);
		return parent[x];
	}
}