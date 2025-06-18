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
    static int n;
    static Point[] points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        points = new Point[n];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        double sum = 0;

        for (int i = 0; i < n; i++) {
            sum += (double) points[i].x * points[(i + 1) % n].y;
            sum -= (double) points[i].y * points[(i + 1) % n].x;
        }

        System.out.printf("%.1f", Math.abs(sum / 2));
    }
}
