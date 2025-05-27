import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge>{
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
    static int n, m;
    static int[] dist;
    static int[] prev;
    static final int INF = 100_000_000;
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Edge(to, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        prev = new int[n + 1];
        dijkstra(start);

        //비용 출력
        System.out.println(dist[dest]);
        List<Integer> path = new ArrayList<>();
        int node = dest;
        while (node != 0) {
            path.add(node);
            node = prev[node];
        }
        Collections.reverse(path);
        System.out.println(path.size());
        for(int city : path) {
            System.out.print(city + " ");
        }
    }

    static void dijkstra(int start) {
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(dist[cur.dest] < cur.weight) continue;

            for (Edge next : graph[cur.dest]) {
                int nextDist = cur.weight + next.weight;
                if (dist[next.dest] > nextDist) {
                    dist[next.dest] = nextDist;
                    prev[next.dest] = cur.dest;
                    pq.add(new Edge(next.dest, nextDist));
                }
            }
        }
    }
}
