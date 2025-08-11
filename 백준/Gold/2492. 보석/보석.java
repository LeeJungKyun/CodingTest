import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    static int n, m, t, k;
    static List<Point> invaders = new ArrayList<>();

    public static int countInRange(int x, int y) {
        int count = 0;
        for (Point p : invaders) {
            if (x <= p.x && p.x <= x + k && y <= p.y && p.y <= y + k) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 가로
        m = Integer.parseInt(st.nextToken()); // 세로
        t = Integer.parseInt(st.nextToken()); // 침입자 수
        k = Integer.parseInt(st.nextToken()); // 감시 범위 길이

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            invaders.add(new Point(x, y));
        }

        int maxCount = 0;
        int bestX = 0, bestY = 0;

        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                int xx = invaders.get(i).x;
                int yy = invaders.get(j).y;

                if (xx + k > n) xx = n - k;
                if (yy + k > m) yy = m - k;

                int currentCount = countInRange(xx, yy);
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    bestX = xx;
                    bestY = yy + k; // 출력은 오른쪽 위 점의 y좌표
                }
            }
        }

        System.out.println(bestX + " " + bestY);
        System.out.println(maxCount);
    }
}
