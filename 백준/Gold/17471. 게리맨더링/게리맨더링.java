import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N개의 구역으로 나누어져있음 (1 ~ N 번호)
 * 구역을 두 개의 선거구로 나눌거임 -> 비트마스킹으로 나누면 될듯
 * 한 선거구에 포함되어 있는 구역은 모두 연결되어있어야 한다. (거쳐서 가든 어케든 가면 됨)
 *
 * 1. 구역의 수 N주어짐
 * 2. 둘 째줄에 1번 구역부터 N번 구역까지 주어짐
 * 3. 인접한 구역의 정보를 입력 ( 첫번째 숫자: 개수, 두번째부터 각 구역의 번호) -> 인접리스트로 구현
 * 4. 비트마스킹으로 조합 생성
 */
public class Main {
	static ArrayList<Integer>[] adjList;
	static int[] population;
	static int n;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. N 입력
		n = Integer.parseInt(br.readLine());
		
		//2. 인구수 입력
		population = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++)
			population[i] = Integer.parseInt(st.nextToken());
		
		//3. 인접한 구역의 정보를 입력 ( 첫번째 숫자: 개수, 두번째부터 각 구역의 번호) -> 인접리스트로 구현
		adjList = new ArrayList[n + 1];
		
		for(int i = 1; i <= n; i++)
			adjList[i] = new ArrayList<>();
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int adjCount = Integer.parseInt(st.nextToken());
			for(int index = 0; index < adjCount; index++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[i].add(to);
				adjList[to].add(i);
			}
		}
		
		//4. 비트마스킹으로 조합 생성
		for(int mask = 1; mask < (1 << n); mask++) {
			//조합은 생성되었으니깐 그룹 나누기
			ArrayList<Integer> groupA = new ArrayList<>();
			ArrayList<Integer> groupB = new ArrayList<>();
			
			for(int i = 1; i <= n; i++) {
				//그룹 나누기
				if((mask & (1 << (i - 1))) == 0) {
					groupA.add(i);
				} else {					
					groupB.add(i);
				}
			}
			
			//그룹 다 나눴으면 두 그룹이 각각 다 내부애들끼리 연결되어있는지 확인하고 연결되어있다면 차이를 구해서 최소 차이 갱신
			if(isConnected(groupA) && isConnected(groupB)) {
				int aSum = 0, bSum = 0;
				for(int num : groupA)
					aSum += population[num];
				for(int num : groupB)
					bSum += population[num];
				
				minDiff = Math.min(minDiff, Math.abs(aSum - bSum));
			}
		}
		
		System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
	}
	
	public static boolean isConnected(ArrayList<Integer> group) {
		//그룹의 사이즈가 1이면 확인할 필요 없음
		if(group.size() <= 1)
			return true;
		boolean[] visited = new boolean[n + 1];
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		int startCity = group.get(0);
		visited[startCity] = true;
		queue.add(startCity);
		int count = 1;
		
		while(!queue.isEmpty()) {
			int curCity = queue.poll();
			
			//인접한 도시들에 대해 방문하지 않았다면 
			for(int nextCity : adjList[curCity]) {
				if(group.contains(nextCity) && !visited[nextCity]) {
					visited[nextCity] = true;
					queue.add(nextCity);
					count++;
				}
			}
		}
		
		//group의 size와 같으면 된거니깐 true
		return count == group.size();
	}
}