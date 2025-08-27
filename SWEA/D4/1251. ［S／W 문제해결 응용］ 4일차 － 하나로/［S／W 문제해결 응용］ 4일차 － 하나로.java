import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N개의 섬 연결해야함
 * * 해저터널 건설 비용 : 세율(E) * 해저터널 길이의 제곱(L^2)
 * 64bit integer 및 double로 처리해야함
 *
 * 1. 테스트케이스 입력
 * 2. N입력
 * 3. N줄에 섬의 좌표 입력 첫 줄에 x, 다음 줄에 y
 * 4. 부담 세율 실수 E 입력 (double)
 * 5. 필요 변수 초기화
 * 6. Prim 알고리즘을 이용해 최소 신장 트리(MST) 비용 계산
 * 7. 출력
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
        int end;
        double weight;
        public Edge(int end, double weight) {
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
    static int n;
    static Island[] islandArr;
    static double[][] adjMatrix;
    
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
            islandArr = new Island[n];
            long[] x = new long[n];
            long[] y = new long[n];
            
            //x좌표 입력
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }
            
            //y좌표 입력
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }
            
            for(int i = 0; i < n; i++) {
                islandArr[i] = new Island(x[i], y[i]);
            }
            
            //4. 부담 세율 실수 E 입력 (double)
            E = Double.parseDouble(br.readLine());
            
            // 5. 인접 행렬로 그래프 구성
            adjMatrix = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double weight = calculateDistance(islandArr[i], islandArr[j]);
                    adjMatrix[i][j] = adjMatrix[j][i] = weight;
                }
            }
            
            // 6. Prim 알고리즘 실행
            double minCost = prim();
            
            // 7. 출력
            sb.append('#').append(tc).append(' ').append(Math.round(minCost * E)).append('\n');
        }
        System.out.println(sb);
    }

    public static double prim() {
        double minTotalCost = 0;
        
        // MST에 포함된 정점인지 여부를 나타내는 배열
        boolean[] visited = new boolean[n];
        
        // 우선순위 큐(PriorityQueue)를 사용하여 현재 MST에 연결할 수 있는 최소 비용의 간선을 찾음
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        // 시작 정점(0번 섬)에서 시작
        pq.offer(new Edge(0, 0));
        
        int count = 0; // 연결된 정점 수
        
        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int currentIsland = currentEdge.end;
            double currentWeight = currentEdge.weight;
            
            // 이미 방문한 정점이면 건너뛰기
            if (visited[currentIsland]) {
                continue;
            }
            
            // 현재 정점을 MST에 포함시키고 비용 추가
            visited[currentIsland] = true;
            minTotalCost += currentWeight;
            count++;
            
            // 모든 정점을 연결했다면 종료
            if (count == n) {
                break;
            }
            
            // 현재 정점에서 연결된 모든 간선을 우선순위 큐에 추가
            for (int i = 0; i < n; i++) {
                if (!visited[i] && i != currentIsland) {
                    pq.offer(new Edge(i, adjMatrix[currentIsland][i]));
                }
            }
        }
        
        return minTotalCost;
    }
    
    public static double calculateDistance(Island island1, Island island2) {
        long dx = island1.x - island2.x;
        long dy = island1.y - island2.y;
        return (double) (dx * dx + dy * dy);
    }
}