import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, H;
    static int[][][] arr;
    static boolean[][][] visited;
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static Queue<Tomato> q = new LinkedList<>();

    static class Tomato {
        int x, y, z, day;

        public Tomato(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        arr = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    arr[h][n][m] = Integer.parseInt(st.nextToken());
                    if (arr[h][n][m] == 1) {
                        q.offer(new Tomato(m, n, h, 0));
                        visited[h][n][m] = true;
                    }
                }
            }
        }

        if (checkTomato()) {
            System.out.println(0);
            return;
        }

        bfs();
    }

    public static void bfs() {
        int day = 0;

        while (!q.isEmpty()) {
            Tomato t = q.poll();
            day = t.day;

            for (int i = 0; i < 6; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                int nz = t.z + dz[i];

                if (nz < 0 || nz >= H || ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (visited[nz][ny][nx] || arr[nz][ny][nx] != 0) continue;

                arr[nz][ny][nx] = 1;
                visited[nz][ny][nx] = true;
                q.add(new Tomato(nx, ny, nz, day + 1));
            }
        }

        if (checkTomato()) {
            System.out.println(day);
        } else {
            System.out.println(-1);
        }
    }

    public static boolean checkTomato() {
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (arr[h][n][m] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}