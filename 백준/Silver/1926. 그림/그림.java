import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static Queue<Pair> q;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int n;
    static int m;
    static int[][] board;
    static int drawCount = 0;
    static int drawSize = 0;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((board[i][j] == 1 && !visited[i][j])) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(drawCount);
        System.out.println(drawSize);
    }

    public static void bfs(int x, int y) {
        q = new LinkedList<>();
        q.add(new Pair(x, y));
        int count = 0;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            count++;
            for (int dir = 0; dir < 4; dir++) {
                int nx = pair.x + dx[dir];
                int ny = pair.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (visited[nx][ny] || board[nx][ny] == 0)
                    continue;
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
            }
        }
        drawCount++;
        if (count > 1)
            count--;
        drawSize = Math.max(drawSize, count);
    }
}