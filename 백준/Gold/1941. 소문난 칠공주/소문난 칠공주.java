import java.io.*;
import java.util.*;

/**
 * 소문난 칠공주 규칙
 * 7명의 여학생들로 구성
 * 7명의 자리는 서로 가로나 세로로 반드시 인접
 * 같은 파로 구성될 이유는 없지만 7명 중 4명 이상은 반드시 포함
 *
 * S = 이다솜파, Y = 임도연파
 */
public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static final int SIZE = 5;
    static char[][] arr;
    static int result = 0;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Point[] selectedPoint;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        //배치도 입력
        arr = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            String input = br.readLine();
            for (int j = 0; j < SIZE; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        selectedPoint = new Point[7];

        selectSeven(0, 0);

        System.out.println(result);
    }

    public static void selectSeven(int id, int selectedNum) {
        if (selectedNum == 7) {
            //다솜파가 4명 이상이고 모든 좌석이 연결되었으면 경우의 수 증가
            if (isValid() && isConnected()) {
                result++;
            }
            return;
        }

        //끝까지 본 경우
        if(id == 25)
            return;

        int x = id / 5;
        int y = id % 5;

        selectedPoint[selectedNum] = new Point(x, y);
        selectSeven(id + 1, selectedNum + 1);

        //현재 좌석 선택 X
        selectSeven(id + 1, selectedNum);
    }

    public static boolean isValid() {
        int sCount = 0;
        for (Point point : selectedPoint) {
            if (arr[point.x][point.y] == 'S') {
                sCount++;
            }
        }
        //이다솜파가 4명 이상이어야함
        return sCount >= 4;
    }

    public static boolean isConnected() {
        boolean[][] selected = new boolean[SIZE][SIZE];
        //뽑힌애들에 대해서 방문처리
        for (Point point : selectedPoint) {
            selected[point.x][point.y] = true;
        }

        ArrayDeque<Point> queue = new ArrayDeque<>();
        visited = new boolean[SIZE][SIZE];

        queue.add(selectedPoint[0]);
        visited[selectedPoint[0].x][selectedPoint[0].y] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) continue;
                if(!selected[nx][ny] || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
                count++;
            }
        }
        return count == 7;
    }
}
