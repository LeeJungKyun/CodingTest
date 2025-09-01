import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * n개의 유적지 ( 0 ~ n - 1)
 * m개의 유적지 연결 정보에 따라 통로를 설치
 * 사이클 최초 발생시 프로젝트 중단
 * 
 * 1. n, m 입력
 * 2. parent 배열 초기화
 * 3. m줄에 연결정보 입력
 * 4. 입력 받은 출발지와 목적지가 연결이 안되어있다면 union 하고 다리개수 증가 -> m개 다 연결했으면 끝
 * 5. find(from)과 find(to) 가 같으면 이제 사이클이 발생하는데 일단 다리개수는 증가시키고 끝
 * 6. count가 m이면 사이클 없으니깐 0, 아니면 count 출력
 */
public class Main {
	static ArrayList<Integer>[] graph;
	static int[] parent;
	static int n, m;
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		//--------------솔루션 코드를 작성하세요.---------------------------
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n, m 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//2. parent 배열 초기화
		parent = new int[n];
		for(int i = 0; i < n; i++)
			parent[i] = i;
		
		//3. m줄에 연결정보 입력
		graph = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int count = 0;
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
			
			//4. 입력 받은 출발지와 목적지가 연결이 안되어있다면 union 하고 다리개수 증가
			if(find(from) != find(to)) {
				union(from, to);
				count++;
				//m개 다 연결했으면 끝
				if(count == m) {
					break;
				}
			}
			//5. find(from)과 find(to) 가 같으면 이제 사이클이 발생하는데 일단 다리개수는 증가시키고 끝
			else {
				count++;
				System.out.print(count);
				return;
			}
		}
		
		//6. count가 m이면 사이클 없으니깐 0, 아니면 count 출력
		System.out.print(count == m ? 0 : count);
	}
	
	public static int find(int x) {
		if(x == parent[x])
			return x;
		else return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y)
			parent[y] = x;
	}
}
