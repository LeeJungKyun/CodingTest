import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N개의 섬 연결해야함
 * 
 * 해저터널 건설 비용 : 세율(E) * 해저터널 길이의 제곱(L^2)
 * 64bit integer 및 double로 처리해야함
 * 
 * 1. 테스트케이스 입력
 * 2. N입력
 * 3. N줄에 섬의 좌표 입력 첫 줄에 x, 다음 줄에 y
 * 4. 부담 세율 실수 E 입력 (double)
 * 5. 필요 변수 초기화
 * 6. 모든 간선 연결해서 넣기 -> 간선 개수: N(N-1)
 * 7. 간선 정렬
 * 8. 비용순으로 정렬된 간선을 순회하면서 간선의 start와 dest가 같은 그룹이 아니라면 비용 더하고, union
 * 9. 출력
 *
 */
public class Solution {
	static class Island {
		long x, y;
		public Island(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int start, end;
		double weight;
		public Edge(int start, int end, double weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}	
	}
	
	static int testCase;
	static double E;
	static double minCost;
	static int n;
	static Island[] islandArr;
	static int[] parent;
	static long[] x, y;
	static ArrayList<Edge> graph;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			
			//2. N입력
			n = Integer.parseInt(br.readLine());
			
			//3. N줄에 섬의 좌표 입력 첫 줄에 x, 다음 줄에 y
			islandArr = new Island[n + 1];
			x = new long[n + 1];
			y = new long[n + 1];
			//x좌표 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				x[i] = Long.parseLong(st.nextToken());
			}
			
			//y좌표 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}
			
			for(int i = 1; i <= n; i++) {
				islandArr[i] = new Island(x[i], y[i]);
			}
			
			//4. 부담 세율 실수 E 입력 (double)
			E = Double.parseDouble(br.readLine());
			
			//5. 필요 변수 초기화
			minCost = 0;
			parent = new int[n + 1];
			for(int i = 1; i <= n; i++)
				parent[i] = i;
			graph = new ArrayList<>();
			
			//6. 모든 간선 연결해서 넣기 -> 간선 개수: N(N-1)
			for(int i = 1; i <= n; i++) {
				Island curIsland = islandArr[i];
				for(int j = i + 1; j <= n; j++) {
					Island compareIsland = islandArr[j];
					double weight = calculateDistance(curIsland, compareIsland);
					graph.add(new Edge(i, j, weight));
				}
			}
			
			//7. 간선 정렬
			Collections.sort(graph);
			
			//8. 비용순으로 정렬된 간선을 순회하면서 간선의 start와 dest가 같은 그룹이 아니라면 비용 더하고, union
			int edgeCount = 0;
			for(Edge edge : graph) {
				if(find(edge.start) != find(edge.end)) {
					union(edge.start, edge.end);
					minCost += edge.weight;
					edgeCount++;
					if(edgeCount == n - 1)
						break;
				}
			}
			
			//9. 출력
			sb.append('#').append(tc).append(' ').append(Math.round(minCost * E)).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int find(int x) {
		if(x == parent[x])
			return x;
		else return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) {
			parent[y] = x;
		}
	}
	
	 public static double calculateDistance(Island island1, Island island2) {
	        long dx = island1.x - island2.x;
	        long dy = island1.y - island2.y;
	        return (double) (dx * dx + dy * dy);
	    }
}