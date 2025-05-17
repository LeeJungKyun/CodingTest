import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int testCase;
    static int w, h;
    static int arr[][];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<Point> fires;
    static Point start;
    static boolean[][] visited;
    static int result;
    static int[][] fireTime;
    static int[][] escapeTime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            arr = new int[h][w];
            fires = new ArrayList<>();

            //배열 입력 시작
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    //빈공간일 경우 0
                    if(line.charAt(j) == '.')
                        arr[i][j] = 0;
                    //벽일 경우 1
                    else if(line.charAt(j) == '#')
                        arr[i][j] = 1;
                    //불일 경우
                    else if(line.charAt(j) == '*') {
                        arr[i][j] = 2;
                        fires.add(new Point(i, j));
                    } else
                        start = new Point(i, j);
                }
            }
            //입력 끝

            if (escape()) {
                System.out.println(result);
            } else System.out.println("IMPOSSIBLE");
        }
    }

    // ------- 함수 -------

    public static boolean escape() {
        fireTime = new int[h][w];
        escapeTime = new int[h][w];

        for (int[] row : fireTime) Arrays.fill(row, -1);
        for (int[] row : escapeTime) Arrays.fill(row, -1);

        bfsFire();
        return bfsPerson();
    }

    public static void bfsFire() {
        Queue<Point> queue = new LinkedList<>();
        for (Point fire : fires) {
            queue.add(fire);
            fireTime[fire.x][fire.y] = 0;
        }

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                if (arr[nx][ny] == 1 || fireTime[nx][ny] != -1) continue;

                fireTime[nx][ny] = fireTime[cur.x][cur.y] + 1;
                queue.add(new Point(nx, ny));
            }
        }
    }

    public static boolean bfsPerson(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        escapeTime[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                // 탈출 조건
                if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                    result = escapeTime[cur.x][cur.y] + 1;
                    return true;
                }

                if (arr[nx][ny] == 1 || escapeTime[nx][ny] != -1) continue;

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


}
