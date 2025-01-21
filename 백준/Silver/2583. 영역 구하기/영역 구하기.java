import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int m, n, k;
    static int[][] arr;
    static boolean[][] visited;
    static int count;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[m][n];
        visited = new boolean[m][n];

        for (int K = 0; K < k; K++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = m - Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = m - Integer.parseInt(st.nextToken());

            int startX = Math.min(x1, x2);
            int endX = Math.max(x1, x2);
            int startY = Math.min(y1, y2);
            int endY = Math.max(y1, y2);

            for (int y = startY; y < endY; y++) {
                for (int x = startX; x < endX; x++) {
                    arr[y][x] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j] && arr[i][j] == 0) {
                    count = 1;
                    dfs(i, j);
                    result.add(count);
                }
            }
        }
        System.out.println(result.size());

        Collections.sort(result);

        for (Integer i : result) {
            System.out.print(i + " ");
        }
    }

    public static void dfs(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            if (!visited[ny][nx] && arr[ny][nx] == 0) {
                count++;
                dfs(ny, nx);
            }
        }
    }
}
