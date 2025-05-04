import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(ans);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[n][m];
        queue.add(new int[]{x, y, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int dir = 0; dir < 8; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    if (arr[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, cur[2] + 1});
                    } else{
                        ans = Math.max(ans, cur[2] + 1);
                        return;
                    }
                }
            }
        }
    }
}
