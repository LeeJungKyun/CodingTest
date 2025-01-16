import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;
    static int MAX = 100000;

    public static class Point {
        int index;
        int time;

        public Point(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[1000001];
        bfs();
        System.out.println(result);
    }

    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(n, 0));

        while (!q.isEmpty()) {
            Point point = q.poll();
            visited[point.index] = true;
            if (point.index == k) {
                result = Math.min(result, point.time);
            }

            if (point.index * 2 <= MAX && !visited[point.index * 2]) {
                q.offer(new Point(point.index * 2, point.time));
            }
            if (point.index + 1 <= MAX && !visited[point.index + 1]) {
                q.offer(new Point(point.index + 1, point.time + 1));
            }
            if (point.index - 1 >= 0 && !visited[point.index - 1]) {
                q.offer(new Point(point.index - 1, point.time + 1));
            }
        }
    }
}
