import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
    static int n, m;
    static int[][] dist;
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        dist = new int[n][n];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start, weight));
        }

        for (int i = 1; i <= n; i++)
            dijkstra(i);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j)
                    sb.append('-').append(" ");
                else
                    sb.append(dist[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static void dijkstra(int start) {
        boolean[] visited = new boolean[n + 1];
        int[] min = new int[n + 1];

        for(int i = 1; i <= n; i++)
            min[i] = Integer.MAX_VALUE;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        min[start] = 0;
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(min[cur.dest] < cur.weight) continue;

            if(!visited[cur.dest])
                visited[cur.dest] = true;

            for(Edge next : graph[cur.dest]){
                if (!visited[next.dest] && cur.weight + next.weight < min[next.dest]) {
                    min[next.dest] = cur.weight + next.weight;
                    dist[next.dest - 1][start - 1] = cur.dest;
                    pq.add(new Edge(next.dest, min[next.dest]));
                }
            }
        }
    }
}
