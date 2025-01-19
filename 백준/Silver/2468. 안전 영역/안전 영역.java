import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int maxHeight = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] > maxHeight) {
                    maxHeight = arr[i][j];
                }
            }
        }

        int max = 0;

        for (int height = 0; height < maxHeight; height++) {
            visited = new boolean[n][n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && arr[i][j] > height) {
                        cnt += dfs(i, j, height);
                    }
                }
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }

    public static int dfs(int x, int y, int height) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1) {
                continue;
            }
            if (visited[nx][ny]) {
                continue;
            }
            if (arr[nx][ny] > height) {
                dfs(nx, ny, height);
            }
        }
        return 1;
    }
}
