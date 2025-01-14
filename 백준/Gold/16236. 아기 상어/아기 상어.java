import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x;
        int y;
        int dist;

        public Point(int y, int x, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int n;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int result;
    static int size = 2;
    static int remain = 2;
    static int sharkX, sharkY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkY = i;
                    sharkX = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            int ret = bfs(sharkY, sharkX);
            if (ret == -1) {
                break;
            }

            result += ret;
            remain--;

            if (remain == 0) {
                size++;
                remain = size;
            }
        }
        System.out.println(result);
    }

    public static int bfs(int cy, int cx) {
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.dist == o2.dist && o1.y == o2.y) {
                    return o1.x - o2.x;
                } else if (o1.dist == o2.dist) {
                    return o1.y - o2.y;
                }
                return o1.dist - o2.dist;
            }
        });

        boolean[][] visited = new boolean[n][n];
        pq.add(new Point(cy, cx, 0));
        visited[cy][cx] = true;

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            //0이 아니고 상어의 크기보다 작으면 물고기 먹기
            if (map[cur.y][cur.x] > 0 && map[cur.y][cur.x] < size) {
                map[cur.y][cur.x] = 0;
                sharkY = cur.y;
                sharkX = cur.x;
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                    continue;
                }
                if (map[ny][nx] > size) {
                    continue;
                }
                if (visited[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                pq.add(new Point(ny, nx, cur.dist + 1));
            }
        }
        return -1;
    }
}
