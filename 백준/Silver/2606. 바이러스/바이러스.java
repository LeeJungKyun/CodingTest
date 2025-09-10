import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * BFS
 * 
 */
public class Main {
	static int computerCount;
	static int pairCount;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//컴퓨터의 개수 입력
		computerCount = Integer.parseInt(br.readLine());
		
		//인접리스트, 방문배열 초기화
		adjList = new ArrayList[computerCount + 1];
		for(int i = 1; i <= computerCount; i++)
			adjList[i] = new ArrayList<>();
		
		visited = new boolean[computerCount + 1];
		
		//인접 쌍 개수 입력
		pairCount = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < pairCount; i++) {
			st = new StringTokenizer(br.readLine());
			int computer1 = Integer.parseInt(st.nextToken());
			int computer2 = Integer.parseInt(st.nextToken());
			
			//양방향 연결
			adjList[computer1].add(computer2);
			adjList[computer2].add(computer1);
		}
		
		//1번 바이러스로부터 시작
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			int currentComputer = queue.poll();
			
			for(int nextComputer : adjList[currentComputer]) {
				if(!visited[nextComputer]) {
					visited[nextComputer] = true;
					queue.offer(nextComputer);
				}
			}
		}
		//BFS끝
		//감염된 컴퓨터 확인
		int count = 0;
		for(boolean b : visited) {
			if(b) count++;
		}

		System.out.println(count - 1);
	}
}