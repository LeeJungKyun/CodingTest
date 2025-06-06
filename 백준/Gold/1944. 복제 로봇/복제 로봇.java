import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int n, m;
    static Point start;
    static ArrayList<Point> nodes = new ArrayList<>();
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char ch = line.charAt(j);
                if (ch == '1') map[i][j] = 1;
                else {
                    map[i][j] = 0;
                    if (ch == 'S') nodes.add(0, new Point(i, j)); // S는 index 0
                    else if (ch == 'K') nodes.add(new Point(i, j));
                }
            }
        }

        int size = nodes.size();

        for (int i = 0; i < size; i++) {
            int[][] dist = bfs(nodes.get(i));
            for (int j = i + 1; j < size; j++) {
                int distance = dist[nodes.get(j).x][nodes.get(j).y];
                if (distance == -1) {
                    System.out.println(-1);
                    return;
                }
                edges.add(new Edge(i, j, distance));
            }
        }

        Collections.sort(edges);
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        int result = 0;
        int count = 0;
        for (Edge edge : edges) {
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                result += edge.weight;
                count++;
                if(count == size - 1)
                    break;
            }
        }

        System.out.println(count == size - 1 ? result : -1);

    }

    public static int[][] bfs(Point start) {
        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        dist[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 1 && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
        return dist;
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
