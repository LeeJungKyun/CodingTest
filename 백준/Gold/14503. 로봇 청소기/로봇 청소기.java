import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1은 벽이고 0은 빈칸
 * 1. 청소 안됐으면 청소
 * 2. 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
 *      1. 바라보는 방향을 유지한 채로 한칸 후진할수 있으면 후진하고 1번으로 돌아감
 *      2. 바라보는 방향의 뒤쪽 칸이 벽이 후진할 수 없다면 작동을 멈춘다.
 *      3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
 *          1. 반시계 방향으로 90도 회전
 *          2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
 *          3. 1번으로 돌아간다.
 */
public class Main {
    static int n, m;
    static int r, c, d;
    static int[][] arr;
    static boolean[][] visit;
    //북, 동, 남, 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int count = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visit = new boolean[n][m];

        st = new StringTokenizer(br.readLine());

        //초기 로봇청소기 좌표 r, c
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        //북, 동, 남, 서 (0, 1, 2, 3)
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);

        System.out.println(count);
    }

    public static void dfs (int x, int y, int dir) {
        //청소
        arr[x][y] = -1;

        for (int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (arr[nx][ny] == 0) {
                    count++;
                    dfs(nx, ny, dir);
                    return;
                }
            }
        }

        //후진
        int d = (dir + 2) % 4;
        int bx = x + dx[d];
        int by = y + dy[d];
        if (bx >= 0 && bx < n && by >= 0 && by < m && arr[bx][by] != 1) {
            dfs(bx, by, dir);
        }
    }
}
