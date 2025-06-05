import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static ArrayList<Edge> graph;
    static class Edge implements Comparable<Edge>{
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.add(new Edge(from, to, weight));
        }

        Collections.sort(graph);

        int result = 0;
        int maxCost = 0;
        for(Edge edge : graph){
            //같은 사이클 내에 있지 않다면
            if (find(edge.start) != find(edge.end)) {
                result += edge.weight;
                union(edge.start, edge.end);

                maxCost = edge.weight;
            }
        }

        System.out.println(result - maxCost);
    }

    public static int find(int x){
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y){
            parent[y] = x;
        }
    }
}
