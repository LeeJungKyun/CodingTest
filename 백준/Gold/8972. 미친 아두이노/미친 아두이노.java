import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }
    }

    static int R, C;
    static char[][] map;
    static List<Point> robots = new ArrayList<>();
    static Point player;
    static String cmd;
    static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'I') player = new Point(i, j);
                else if (map[i][j] == 'R') robots.add(new Point(i, j));
            }
        }

        cmd = br.readLine();

        for (int turn = 0; turn < cmd.length(); turn++) {
            int dir = cmd.charAt(turn) - '0';

            // 1. 종수 이동
            int nx = player.x + dx[dir];
            int ny = player.y + dy[dir];
            if (isRobot(nx, ny)) {
                System.out.println("kraj " + (turn + 1));
                return;
            }
            player.x = nx;
            player.y = ny;

            // 2. 아두이노 이동
            Map<String, Integer> countMap = new HashMap<>();
            List<Point> nextRobots = new ArrayList<>();
            for (Point robot : robots) {
                int minDist = Integer.MAX_VALUE;
                int rx = robot.x, ry = robot.y;
                for (int d = 1; d <= 9; d++) {
                    int tx = robot.x + dx[d];
                    int ty = robot.y + dy[d];
                    if (isIn(tx, ty)) {
                        int dist = Math.abs(tx - player.x) + Math.abs(ty - player.y);
                        if (dist < minDist) {
                            minDist = dist;
                            rx = tx;
                            ry = ty;
                        }
                    }
                }
                nextRobots.add(new Point(rx, ry));
                countMap.put(rx + "," + ry, countMap.getOrDefault(rx + "," + ry, 0) + 1);
            }

            // 3. 충돌 및 사망 판정
            robots.clear();
            for (Point robot : nextRobots) {
                if (robot.x == player.x && robot.y == player.y) {
                    System.out.println("kraj " + (turn + 1));
                    return;
                }
                String key = robot.x + "," + robot.y;
                if (countMap.get(key) == 1) {
                    robots.add(robot);
                }
            }
        }

        // 4. 최종 맵 출력
        char[][] finalMap = new char[R][C];
        for (int i = 0; i < R; i++) Arrays.fill(finalMap[i], '.');
        finalMap[player.x][player.y] = 'I';
        for (Point robot : robots) finalMap[robot.x][robot.y] = 'R';

        StringBuilder sb = new StringBuilder();
        for (char[] row : finalMap) {
            for (char c : row) sb.append(c);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static boolean isRobot(int x, int y) {
        for (Point robot : robots) {
            if (robot.x == x && robot.y == y) return true;
        }
        return false;
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}