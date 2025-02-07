import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, e;
    static ArrayList<Node>[] list;
    static int[] dist;
    static boolean[] visited;
    static int v1, v2;
    static int res1, res2, res;
    static final int INF = 200000000;

    static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return weight - node.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());

        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        res1 += dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, n);

        res2 += dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, n);

        if (res1 >= INF && res2 >= INF) {
            res = -1;
        } else {
            res = Math.min(res1, res2);
        }

        System.out.println(res);

    }

    public static int dijkstra(int start, int end) {
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[n + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;

            if (!visited[cur]) {
                visited[cur] = true;

                for (Node node : list[cur]) {
                    if (!visited[node.end] && dist[node.end] > dist[cur] + node.weight) {
                        dist[node.end] = dist[cur] + node.weight;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }
        return dist[end];
    }
}
