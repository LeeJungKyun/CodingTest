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

    static int n;
    static int[][] arr;
    static int[] parent;
    static long result = 0;
    static ArrayList<Edge> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if(i != j)
                    graph.add(new Edge(i, j, weight));
            }
        }

        Collections.sort(graph);

        for (Edge edge : graph) {
            if (find(edge.start) != find(edge.dest)) {
                union(edge.start, edge.dest);
                result += edge.weight;
            }
        }

        System.out.println(result);
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
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
