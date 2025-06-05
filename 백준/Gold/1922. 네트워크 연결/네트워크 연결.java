import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge>{
        int start, dest, weight;

        public Edge(int start, int dest, int weight) {
            this.start = start;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static int[] parent;
    static int n, m;
    static int result;
    static ArrayList<Edge> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        graph = new ArrayList<>();
        StringTokenizer st = null;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.add(new Edge(start, dest, weight));
        }

        Collections.sort(graph);

        for (Edge edge : graph) {
            if (find(edge.start) != find(edge.dest)) {
                result += edge.weight;
                union(edge.start, edge.dest);
            }
        }

        System.out.println(result);
    }

    public static int find(int x) {
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }
}
