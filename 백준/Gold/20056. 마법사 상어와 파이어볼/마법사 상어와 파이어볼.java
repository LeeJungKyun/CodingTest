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

    static class FireBall {
        Point point;
        int m, s, d;

        public FireBall(Point point, int m, int s, int d) {
            this.point = point;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int n, m, k;
    static ArrayList<FireBall> ballList;
    static ArrayList<FireBall>[][] map;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ballList = new ArrayList<>();

        //m개의 파이어볼
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            ballList.add(new FireBall(new Point(r, c), m, d, s));
        }

        //이동 명령
        while (k-- > 0) {
            move();
        }

        int sum = 0;
        for (FireBall fb : ballList) {
            sum += fb.m;
        }

        System.out.println(sum);
    }

    public static void move() {
        map = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (FireBall fb : ballList) {
            int dir = fb.d;
            int s = fb.s % n;

            int nx = (fb.point.x + dx[dir] * s + n) % n;
            int ny = (fb.point.y + dy[dir] * s + n) % n;

            map[nx][ny].add(new FireBall(new Point(nx, ny), fb.m, fb.s, fb.d));
        }

        ballList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!map[i][j].isEmpty()) {
                    if (map[i][j].size() == 1)
                        ballList.add(map[i][j].get(0));
                    else merge(map[i][j]);
                }
            }
        }

    }

    public static void merge(ArrayList<FireBall> list) {
        int massSum = 0, speedSum = 0;
        boolean oddFlag = false, evenFlag = false;
        int[] oddDir = {1, 3, 5, 7};
        int[] evenDir = {0, 2, 4, 6};
        boolean dirFlag = false;

        for (FireBall ball : list) {
            massSum += ball.m;
            speedSum += ball.s;
            if (ball.d % 2 == 0) {
                evenFlag = true;
            } else oddFlag = true;

            //한개라도 어긋난 경우 XOR 값이 1 아니면 0
            //1 : 0, 2, 4, 6
            //0 : 1, 3, 5, 7
            dirFlag = evenFlag ^ oddFlag;
        }

        int mass = massSum / 5;
        if (mass == 0)
            return;
        int speed = speedSum / list.size();
        int[] dirs = dirFlag ? evenDir : oddDir;

        Point p = list.get(0).point;
        for (int d : dirs) {
            ballList.add(new FireBall(new Point(p.x, p.y), mass, speed, d));
        }
    }
}
