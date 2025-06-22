import java.io.*;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return cnt - o.cnt;
        }
    }
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = Character.getNumericValue(input.charAt(j - 1));
            }
        }

        int ans = BFS(1, 1);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(int x, int y) {
        PriorityQueue<Point> q = new PriorityQueue<>();

        q.offer(new Point(x, y, 0));
        boolean[][] visit = new boolean[n + 1][m + 1];
        visit[x][y] = true;

        int nx, ny;
        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == n && p.y == m) {
                return p.cnt;
            }

            for (int i = 0; i < 4; i++) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) {
                    continue;
                }

                if (!visit[nx][ny] && map[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    q.offer(new Point(nx, ny, p.cnt));
                }

                if (!visit[nx][ny] && map[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    q.offer(new Point(nx, ny, p.cnt + 1));
                }
            }
        }
        return 0;
    }

}
