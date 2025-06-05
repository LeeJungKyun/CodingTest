import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Star implements Comparable<Star> {
        int start, end;
        double weight;

        public Star(int start, int dest, double weight) {
            this.start = start;
            this.end = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Star o) {
            return Double.compare(this.weight, o.weight);
        }
    }
    static int n;
    static int[] parent;
    static ArrayList<Point> pointList;
    static ArrayList<Star> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        pointList = new ArrayList<>();
        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            pointList.add(new Point(x, y));
        }

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        //i가 시작, j가 도착지점
        for (int i = 0; i < pointList.size(); i++) {
            for (int j = i + 1; j < pointList.size(); j++) {
                graph.add(new Star(i, j, calculateDistance(pointList.get(i), pointList.get(j))));
            }
        }

        Collections.sort(graph);

        double result = 0;

        for (Star star : graph) {
            if (find(star.start) != find(star.end)) {
                union(star.start, star.end);
                result += star.weight;
            }
        }

        System.out.printf("%.2f\n", result);
    }

    public static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
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
