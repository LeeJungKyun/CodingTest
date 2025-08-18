import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
	    int from, to, weight;

	    Edge(int from, int to, int weight) {
	        this.from = from;
	        this.to = to;
	        this.weight = weight;
	    }

	    @Override
	    public int compareTo(Edge o) {
	        return this.weight - o.weight;
	    }
	}
	static int n, m;
	static int[][] arr;
	static boolean[][] visited;
	static int[] parent;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Integer>[] adjList;
	static List<Edge> edges = new ArrayList<>();
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬에 인덱스 주기
		int islandIndex = 1; // 1부터 시작

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && arr[i][j] == 1) {
					markIsland(i, j, islandIndex);
					islandIndex++;
				}
			}
		}
		
		findBridges();
		
		int result = kruskal(islandIndex - 1);
		
		System.out.println(result);
	}

	//섬에 번호 마킹하기
	static void markIsland(int x, int y, int index) {
	    Stack<int[]> stack = new Stack<>();
	    stack.push(new int[]{x, y});
	    visited[x][y] = true;
	    arr[x][y] = index;

	    while (!stack.isEmpty()) {
	        int[] cur = stack.pop();
	        int cx = cur[0];
	        int cy = cur[1];

	        for (int dir = 0; dir < 4; dir++) {
	            int nx = cx + dx[dir];
	            int ny = cy + dy[dir];

	            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
	                if (!visited[nx][ny] && arr[nx][ny] == 1) {
	                    visited[nx][ny] = true;
	                    arr[nx][ny] = index;
	                    stack.push(new int[]{nx, ny});
	                }
	            }
	        }
	    }
	}
	
	static void findBridges() {
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < m; j++) {
	        	//현재 위치가 섬이라면
	            if (arr[i][j] > 0) {
	                int from = arr[i][j];
	                for (int dir = 0; dir < 4; dir++) {
	                    int length = 0;
	                    int nx = i + dx[dir];
	                    int ny = j + dy[dir];

	                    while (nx >= 0 && ny >= 0 && nx < n && ny < m) {
	                    	// 같은 섬이면 종료
	                        if (arr[nx][ny] == from)
	                        	break;
	                        // 다른 섬 발견
	                        if (arr[nx][ny] > 0) {
	                            if (length >= 2) {
	                                int to = arr[nx][ny];
	                                edges.add(new Edge(from, to, length));
	                            }
	                            break;
	                        }
	                        length++;
	                        nx += dx[dir];
	                        ny += dy[dir];
	                    }
	                }
	            }
	        }
	    }
	}
	
	static int find(int x) {
	    if (parent[x] == x) return x;
	    return parent[x] = find(parent[x]);
	}

	static boolean union(int a, int b) {
	    a = find(a);
	    b = find(b);
	    if (a == b) return false;
	    parent[b] = a;
	    return true;
	}

	static int kruskal(int islandCount) {
	    Collections.sort(edges);

	    parent = new int[islandCount + 1];
	    for (int i = 1; i <= islandCount; i++) {
	        parent[i] = i;
	    }

	    int total = 0;
	    int connected = 0;

	    for (Edge edge : edges) {
	        if (union(edge.from, edge.to)) {
	            total += edge.weight;
	            connected++;
	            if (connected == islandCount - 1) break;
	        }
	    }

	    // 모든 섬이 연결되었는지 확인
	    return (connected == islandCount - 1) ? total : -1;
	}
}
