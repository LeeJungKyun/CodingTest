import java.io.*;
import java.nio.Buffer;
import java.util.*;
/**
 *  N개의 섬으로 이루어진 나라에 몇 개의 섬 사이에는 다리가 설치되어있음
 *  이 다리에는 중량 제한이 있어서 중량제한을 초과하는 양의 물품이 지나가면 다리가 무너진다.
 *
 *  한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하는 프로그램
 *
 *  1. N, M입력 받기
 *  2. M개의 줄에 A, B, C 입력을 받음 (A <-> B 중량제한이 C인 다리가 존재)
 *  3. 공장이 위치해 있는 섬의 번호를 나타내는 두 정수 입력
 */
import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int n, m;
    static int factoryStart, factoryEnd;
    static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        int maxWeight = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
            maxWeight = Math.max(maxWeight, c);
        }

        st = new StringTokenizer(br.readLine());
        factoryStart = Integer.parseInt(st.nextToken());
        factoryEnd = Integer.parseInt(st.nextToken());

        // 이분 탐색 시작
        int low = 1;
        int high = maxWeight;
        int result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canTransport(mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(result);
    }

    // BFS를 이용한 경로 탐색 함수
    public static boolean canTransport(int weight) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.offer(factoryStart);
        visited[factoryStart] = true;

        while (!q.isEmpty()) {
            int currentIsland = q.poll();

            if (currentIsland == factoryEnd) {
                return true;
            }

            for (Node neighbor : adjList[currentIsland]) {
                // 방문하지 않았고, 다리의 중량 제한이 주어진 무게보다 크거나 같을 때만 이동
                if (!visited[neighbor.to] && neighbor.cost >= weight) {
                    visited[neighbor.to] = true;
                    q.offer(neighbor.to);
                }
            }
        }
        return false;
    }
}