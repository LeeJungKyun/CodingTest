import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int m, n;
    static int[] parent;
    static ArrayList<Edge> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            graph = new ArrayList<>();
            int total = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                graph.add(new Edge(start, end, weight));
                total += weight;
            }

            Collections.sort(graph); // 중요!

            int min = 0;

            for (Edge edge : graph) {
                if (find(edge.start) != find(edge.end)) {
                    union(edge.start, edge.end);
                    min += edge.weight;
                }
            }

            sb.append(total - min).append('\n');
        }

        System.out.print(sb);
    }

    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }
}