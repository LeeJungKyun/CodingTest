import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calculateDistance(Point other) {
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        }
    }


    static int T;
    static Point[] points;
    static int n;
    static double destDistance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            points = new Point[n + 2];
            visited = new boolean[n + 2];

            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }

            System.out.println(bfs() ? "happy" : "sad");
        }
    }

    public static boolean bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(points[0]);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            //페스티벌은 인덱스 N+1
            if (cur.equals(points[n + 1])) {
                return true;
            }

            for (int i = 1; i < n + 2; i++) {
                if (!visited[i] && cur.calculateDistance(points[i]) <= 1000) {
                    visited[i] = true;
                    queue.add(points[i]);
                }
            }
        }
        return false;
    }
}
