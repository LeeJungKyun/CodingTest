import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int testCase;
    static int l;
    static int[][] arr;
    static int startX, startY, endX, endY;
    static Point start, end;
    static int[] dx = {-1, -2, -1, -2, 1, 2, 1, 2};
    static int[] dy = {2, 1, -2, -1, 2, 1, -2, -1};
    static boolean[][] visited;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            l = Integer.parseInt(br.readLine());
            arr = new int[l][l];
            visited = new boolean[l][l];

            StringTokenizer st = new StringTokenizer(br.readLine());

            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            start = new Point(startX, startY);

            st = new StringTokenizer(br.readLine());

            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            end = new Point(endX, endY);

            bfs();

            System.out.println(arr[endX][endY]);
        }
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < l && ny >= 0 && ny < l) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        arr[nx][ny] = arr[cur.x][cur.y] + 1;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
    }
}
