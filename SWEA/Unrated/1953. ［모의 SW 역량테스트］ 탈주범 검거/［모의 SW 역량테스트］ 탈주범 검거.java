import java.io.*;
import java.util.*;

public class Solution {
    static class Point {
        int x, y, time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int testCase;
    static int n, m, r, c, l;
    static int[][] arr;
    static int[][] visited;

    static BufferedReader br;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 각 파이프 타입 별 이동 가능한 방향 (0:상, 1:하, 2:좌, 3:우)
    static int[][] pipeDirs = {
        {},                 
        {0, 1, 2, 3},       // 1번: 상하좌우
        {0, 1},             // 2번: 상하
        {2, 3},             // 3번: 좌우
        {0, 3},             // 4번: 상우
        {1, 3},             // 5번: 하우
        {1, 2},             // 6번: 하좌
        {0, 2}              // 7번: 상좌
    };

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            arr = new int[n][m];
            visited = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(new Point(r, c, 1));

            // 결과 계산
            int result = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] > 0 && visited[i][j] <= l) {
                        result++;
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }

        System.out.println(sb);
    }

    public static void bfs(Point start) {
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.x][start.y] = start.time;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.time >= l) continue;

            int pipeType = arr[cur.x][cur.y];

            for (int d : pipeDirs[pipeType]) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (arr[nx][ny] == 0) continue;

                int nextPipe = arr[nx][ny];
                int opp = getOpposite(d);

                boolean canConnect = false;
                for (int nd : pipeDirs[nextPipe]) {
                    if (nd == opp) {
                        canConnect = true;
                        break;
                    }
                }

                if (!canConnect) continue;

                if (visited[nx][ny] == 0 || visited[nx][ny] > cur.time + 1) {
                    visited[nx][ny] = cur.time + 1;
                    queue.add(new Point(nx, ny, cur.time + 1));
                }
            }
        }
    }

    //연결되어있는지 확인
    public static int getOpposite(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }
}
