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

    static int n, k, l;
    static ArrayList<Point> apples;
    static LinkedList<Point> bodyList;
    static Deque<int[]> turns;
    static int[][] arr;
    //상 우 하 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int curDir;
    //머리 위치만 추적
    static Point curPoint;
    static boolean isDead = false;
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        apples = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            apples.add(new Point(row - 1, col - 1));
        }

        curPoint = new Point(0, 0);
        bodyList = new LinkedList<>();
        curDir = 1;

        l = Integer.parseInt(br.readLine());
        turns = new ArrayDeque<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            turns.offer(new int[]{x, d.equals("D") ? 1 : -1});
        }

        move();

        System.out.println(time);
    }

    public static void move() {
        //안죽는동안
        while (!isDead) {
            time++;
            int nx = curPoint.x + dx[curDir];
            int ny = curPoint.y + dy[curDir];

            // 벽 또는 몸과 충돌
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || isBody(nx, ny)) {
                isDead = true;
                return;
            }

            bodyList.addFirst(new Point(curPoint.x, curPoint.y));
            curPoint = new Point(nx, ny);

            // 사과 확인
            boolean ateApple = false;
            for (int i = 0; i < apples.size(); i++) {
                Point apple = apples.get(i);
                if (apple.x == nx && apple.y == ny) {
                    apples.remove(i);
                    ateApple = true;
                    break;
                }
            }

            if (!ateApple) {
                bodyList.removeLast();
            }

            // 방향 전환
            if (!turns.isEmpty() && turns.peek()[0] == time) {
                int[] turn = turns.poll();
                curDir = (curDir + turn[1] + 4) % 4;
            }
        }
    }

    public static boolean isBody(int x, int y) {
        for (Point p : bodyList) {
            if (p.x == x && p.y == y) return true;
        }
        return false;
    }
}
