import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 14502 연구소
 * 사용 로직
 * 1. DFS
 * 2. 백트래킹
 */
public class Main {
    static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] board;
    static int n,m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int max_safe = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(max_safe);
    }

    public static void dfs(int wall) {
        if (wall == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    dfs(wall + 1);
                    board[i][j] = 0;
                }
            }
        }
    }

    public static void bfs() {
        Queue<Pair> virus = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2)
                    virus.add(new Pair(i, j));
            }
        }
        int[][] new_board = new int[n][m];
        for (int i = 0; i < n; i++) {
            new_board[i] = board[i].clone();
        }
        while (!virus.isEmpty()) {
            Pair pair = virus.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = pair.x + dx[dir];
                int ny = pair.y + dy[dir];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (new_board[nx][ny] == 0) {
                        virus.add(new Pair(nx, ny));
                        new_board[nx][ny] = 2;
                    }
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (new_board[i][j] == 0)
                    cnt++;
            }
        }
        max_safe = Math.max(max_safe, cnt);
    }
}
