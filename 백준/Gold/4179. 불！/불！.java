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

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int r, c;
    static int[][] map;
    static int[][] fireTime;
    static int[][] escapeTime;
    static int ans;
    static Point start;
    static ArrayList<Point> firePoints = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        fireTime = new int[r][c];
        escapeTime = new int[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                if (line.charAt(j) == '#') {
                    map[i][j] = 1;
                } else if (line.charAt(j) == '.') {
                    map[i][j] = 0;
                } else if (line.charAt(j) == 'J') {
                    map[i][j] = 0;
                    start = new Point(i, j);
                } else {
                    map[i][j] = 1;
                    firePoints.add(new Point(i, j));
                }
            }
        }

        for (int[] row : fireTime) Arrays.fill(row, -1);
        for (int[] row : escapeTime) Arrays.fill(row, -1);

        bfsFire();
        if (bfsPerson()) {
            System.out.println(ans);
        } else System.out.println("IMPOSSIBLE");
    }

    public static void bfsFire() {
        Queue<Point> queue = new LinkedList<>();
        for (Point fire : firePoints) {
            queue.add(fire);
            fireTime[fire.x][fire.y] = 0;
        }

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (!isInBound(nx, ny))
                    continue;
                if (map[nx][ny] == 1 || fireTime[nx][ny] != -1)
                    continue;

                fireTime[nx][ny] = fireTime[cur.x][cur.y] + 1;
                queue.add(new Point(nx, ny));
            }
        }
    }

    public static boolean bfsPerson() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        escapeTime[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                    ans = escapeTime[cur.x][cur.y] + 1;
                    return true;
                }
                if (map[nx][ny] == 1 || escapeTime[nx][ny] != -1) continue;

                int nextTime = escapeTime[cur.x][cur.y] + 1;
                // 불이 없거나, 불보다 먼저 도착할 수 있을 때만
                if (fireTime[nx][ny] == -1 || fireTime[nx][ny] > nextTime) {
                    escapeTime[nx][ny] = nextTime;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        return false;
    }

    public static boolean isInBound(int x, int y) {
        return (x >= 0 && x < r && y >= 0 && y < c);
    }
}
