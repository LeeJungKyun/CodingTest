import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m, n;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static Queue<Tomato> queue = new LinkedList<Tomato>();

    static class Tomato {
        int x;
        int y;
        int day;

        public Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        boolean checkTomato = true;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    queue.offer(new Tomato(i, j, 0));
                } else if (arr[i][j] == 0) {
                    checkTomato = false;
                }
            }
        }

        if (checkTomato) {
            System.out.println(0);
            return;
        }
        bfs();
    }

    public static void bfs() {
        int day = 0;

        while (!queue.isEmpty()) {
            Tomato t = queue.poll();
            day = t.day;

            for (int i = 0; i < 4; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 0) {
                        arr[nx][ny] = 1;
                        queue.offer(new Tomato(nx, ny, day + 1));
                }
            }
        }

        if (checkTomato()) {
            System.out.println(day);
        } else System.out.println(-1);

    }

    private static boolean checkTomato() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
