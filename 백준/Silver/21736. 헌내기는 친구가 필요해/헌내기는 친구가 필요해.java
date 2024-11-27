import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n, m;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static Queue<Point> queue;
    static Point Start;
    static int result;

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        arr = new int[n][m];
        visited = new boolean[n][m];
        queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);
                if (c == 'O') {
                    arr[i][j] = 0;
                } else if (c == 'I') {
                    arr[i][j] = 2;
                    Start = new Point(i, j);
                } else if (c == 'X') {
                    arr[i][j] = 1;
                } else if (c == 'P') {
                    arr[i][j] = 3;
                }
            }
        }

        queue.add(Start);
        visited[Start.x][Start.y] = true; // 시작점 방문 처리
        bfs();
        System.out.println(result == 0 ? "TT" : result); // 결과 출력
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) { // 경계 조건
                    if (!visited[nx][ny] && arr[nx][ny] != 1) { // 방문하지 않고 벽이 아닌 경우
                        visited[nx][ny] = true; // 방문 처리
                        if (arr[nx][ny] == 3) {
                            result++;
                        }
                        queue.add(new Point(nx, ny)); // 다음 탐색 위치 추가
                    }
                }
            }
        }
    }
}