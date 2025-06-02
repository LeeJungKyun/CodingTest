import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] arr;
    static Queue<Point> queue;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        queue = new LinkedList<>();

        //맨 위에서 빈 곳만 확인
        for (int i = 0; i < m; i++) {
            if (arr[0][i] == 0 && !visited[0][i]) {
                queue.offer(new Point(0, i));
                if (bfs()) {
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
    }

    public static boolean bfs() {
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                //범위 안에 있고 벽이 아니라면
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] != 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    //끝에 다다랐다면
                    if (nx == n - 1) {
                        return true;
                    } else queue.offer(new Point(nx, ny));

                }
            }
        }
        return false;
    }
}
