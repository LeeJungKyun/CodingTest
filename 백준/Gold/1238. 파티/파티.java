import java.util.*;
import java.io.*;

public class Main {
    static int n, m, x;
    static ArrayList<Edge>[] graph;
    static ArrayList<Edge>[] reverseGraph;
    static int[] distGo, distBack;
    static final int INF = 100000000;

    static class Edge implements Comparable<Edge> {
        int dest, weight;
        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        reverseGraph = new ArrayList[n + 1];
        distGo = new int[n + 1];
        distBack = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, time));
            reverseGraph[to].add(new Edge(from, time));
        }

        distGo = dijkstra(reverseGraph, x);
        distBack = dijkstra(graph, x);

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, distGo[i] + distBack[i]);
        }

        System.out.println(maxTime);
    }

    static int[] dijkstra(ArrayList<Edge>[] edge, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.dest] < cur.weight) continue;

            for (Edge next : edge[cur.dest]) {
                int nextDist = cur.weight + next.weight;
                if (dist[next.dest] > nextDist) {
                    dist[next.dest] = nextDist;
                    pq.add(new Edge(next.dest, nextDist));
                }
            }
        }
        return dist;
    }
}