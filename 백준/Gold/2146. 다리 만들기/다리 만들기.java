import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandId = 2;
        visited = new boolean[n][n];

        // 섬 넘버링
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfsNumbering(i, j, islandId++);
                }
            }
        }

        // 각 섬에 대해 가장자리에서 BFS 실행
        for (int id = 2; id < islandId; id++) {
            visited = new boolean[n][n];
            Queue<Point> queue = new LinkedList<>();

            // 현재 섬의 가장자리 수집
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == id) {
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];

                            if (isValid(nx, ny) && map[nx][ny] == 0) {
                                queue.add(new Point(i, j, 0));
                                visited[i][j] = true;
                                break;
                            }
                        }
                    }
                }
            }

            while (!queue.isEmpty()) {
                Point cur = queue.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    if (!isValid(nx, ny) || visited[nx][ny]) continue;
                    if (map[nx][ny] == id) continue; // 같은 섬이면 무시
                    if (map[nx][ny] != 0 && map[nx][ny] != id) {
                        min = Math.min(min, cur.dist);
                        break;
                    }

                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, cur.dist + 1));
                }
            }
        }

        System.out.println(min);
    }

    static void bfsNumbering(int x, int y, int id) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 0));
        visited[x][y] = true;
        map[x][y] = id;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (!isValid(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] != 1) continue;

                visited[nx][ny] = true;
                map[nx][ny] = id;
                q.add(new Point(nx, ny, 0));
            }
        }
    }

    static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}