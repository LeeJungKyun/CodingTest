import java.io.*;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] arr;
    static boolean[] isUsed = new boolean[26];
    static int maxCount = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        isUsed[arr[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(maxCount);
    }

    static void dfs(int x, int y, int count) {
        maxCount = Math.max(maxCount, count);

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                int alphaIndex = arr[nx][ny] - 'A';
                if (!isUsed[alphaIndex]) {
                    isUsed[alphaIndex] = true;
                    dfs(nx, ny, count + 1);
                    isUsed[alphaIndex] = false;
                }
            }
        }
    }
}
