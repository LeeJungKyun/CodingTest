import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // ------- 전역변수 -------
    static int n, m, d, s;
    static int[][] arr;
    static ArrayList<Point> cloudList;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    // ------- 메인 -------
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][n + 1];

        //초기상태 입력
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cloudList = new ArrayList<>();

        // 초기 구름
        cloudList.add(new Point(n, 1));
        cloudList.add(new Point(n, 2));
        cloudList.add(new Point(n - 1, 1));
        cloudList.add(new Point(n - 1, 2));

        //이동 정보 m줄 (d, s)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            move(d - 1, s);
            waterCopy();
            makeCloud();
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum += arr[i][j];
            }
        }

        System.out.println(sum);
    }

    // ------- 함수 -------
    public static void makeCloud() {
        Set<Point> prev = new HashSet<>(cloudList);
        cloudList.clear();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                Point p = new Point(i, j);
                if (arr[i][j] >= 2 && !prev.contains(p)) {
                    arr[i][j] -= 2;
                    cloudList.add(p);
                }
            }
        }
    }

    public static void move(int d, int s) {
        s = s % n;
        //모든 구름들을 d 방향으로 s만큼 이동
        for (Point point : cloudList) {
            //이동 후 좌표 nx, ny
            int nx = ((point.x - 1 + dx[d] * s) % n + n) % n + 1;
            int ny = ((point.y - 1 + dy[d] * s) % n + n) % n + 1;

            //좌표 갱신
            point.x = nx;
            point.y = ny;

            //이동 후 해당 칸에 비 1씩 증가
            arr[point.x][point.y]++;
        }
    }

    //비가 내린 칸에 대해 물복사버그
    public static void waterCopy() {
        int[][] increasing = new int[n + 1][n + 1];
        for (Point point : cloudList) {
            int[] dx = {-1, -1, 1, 1};
            int[] dy = {-1, 1, 1, -1};

            int count = 0;
            for (int dir = 0; dir < 4; dir++) {
                int nx = point.x + dx[dir];
                int ny = point.y + dy[dir];
                //범위 안에 있고 물이 있다면
                if (nx > 0 && nx <= n && ny > 0 && ny <= n && arr[nx][ny] != 0) {
                    count++;
                }
            }
            increasing[point.x][point.y] += count;
        }

        // arr에 반영
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] += increasing[i][j];
            }
        }
    }

    public static void printArr() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
