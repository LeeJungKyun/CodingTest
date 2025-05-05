import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static ArrayList<Point> points;
    static ArrayList<Point> tempPoints;
    static int result = Integer.MAX_VALUE;
    static int totalDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points.add(new Point(x, y));
        }

        for (int i = 0; i < n - 1; i++) {
            totalDistance += calculateDistance(points.get(i), points.get(i + 1));
        }

        //n번째가 빠지면 n - 1 ~ n 이랑 n ~ n + 1 빼고 n - 1에서 n + 1 더하기
        for (int i = 1; i < n - 1; i++) {
            int before = calculateDistance(points.get(i - 1), points.get(i)) + calculateDistance(points.get(i), points.get(i + 1));
            int after = calculateDistance(points.get(i - 1), points.get(i + 1));

            result = Math.min(result, totalDistance - before + after);
        }

        System.out.println(result);
    }

    public static int calculateDistance(Point p1, Point p2) {
        int x = Math.abs(p1.x - p2.x);
        int y = Math.abs(p1.y - p2.y);

        return x + y;
    }
}
