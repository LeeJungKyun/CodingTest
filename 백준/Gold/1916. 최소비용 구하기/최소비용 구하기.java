import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, startCity, endCity;
    static ArrayList<Edge>[] graph;
    static int[] dist;
    static int INF = 100000000;

    static class Edge implements Comparable<Edge> {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Edge(dest, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        startCity = Integer.parseInt(st.nextToken());
        endCity = Integer.parseInt(st.nextToken());

        dijkstra(startCity);

        System.out.println(dist[endCity]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            int curNode = cur.dest;
            int curWeight = cur.weight;

            if (dist[curNode] < curWeight) {
                continue;
            }

            for (Edge edge : graph[curNode]) {
                int nextNode = edge.dest;
                int nextWeight = curWeight + edge.weight;

                if (dist[nextNode] > nextWeight) {
                    dist[nextNode] = nextWeight;
                    pq.add(new Edge(nextNode, nextWeight));
                }
            }
        }
    }
}
