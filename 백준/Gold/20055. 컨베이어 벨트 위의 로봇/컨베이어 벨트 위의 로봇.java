import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int status;
        boolean robot;

        public Point(int status, boolean robot) {
            this.status = status;
            this.robot = robot;
        }
    }

    static Deque<Point> upDeque;
    static Deque<Point> downDeque;
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        //위 컨베이어 : 여기서 pollLast 아래 컨베이어에 addFirst
        upDeque = new ArrayDeque<>();
        //아래 컨베이어 :
        downDeque = new ArrayDeque<>();

        //n개씩 분배
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int stat = Integer.parseInt(st.nextToken());
            upDeque.addLast(new Point(stat, false));
        }

        for (int i = 0; i < n; i++) {
            int stat = Integer.parseInt(st.nextToken());
            downDeque.addLast(new Point(stat, false));
        }

        int ans = 0;

        while (countZero(upDeque) + countZero(downDeque) < k) {
            ans++;
            //벨트 회전
            rotateBelt();
            //로봇 이동
            moveRobot();
            //로봇 올리기
            placeRobot();
        }

        System.out.println(ans);

    }

    public static int countZero(Deque<Point> deque) {
        int count = 0;

        for (Point point : deque) {
            if (point.status == 0)
                count++;
        }
        return count;
    }

    public static void moveRobot() {
        Point[] up = upDeque.toArray(new Point[0]);

        for (int i = n - 2; i >= 0; i--) {
            if (up[i].robot && !up[i + 1].robot && up[i + 1].status > 0) {
                up[i].robot = false;
                up[i + 1].robot = true;
                up[i + 1].status--;
            }
        }

        if (up[n - 1].robot) {
            up[n - 1].robot = false;
        }

        upDeque.clear();
        Collections.addAll(upDeque, up);
    }

    public static void rotateBelt() {
        downDeque.addFirst(upDeque.pollLast());
        upDeque.addFirst(downDeque.pollLast());

        Point[] up = upDeque.toArray(new Point[0]);

        if (up[n - 1].robot) {
            up[n - 1].robot = false;
        }

        upDeque.clear();
        Collections.addAll(upDeque, up);
    }

    public static void placeRobot(){
        Point first = upDeque.peekFirst();
        if (!first.robot && first.status > 0) {
            first.robot = true;
            first.status--;
        }
    }
}
