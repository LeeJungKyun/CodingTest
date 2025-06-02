import java.io.*;
import java.util.*;

public class Main {
    static int r, c, k;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                if(str.charAt(j) == '.'){
                    arr[i][j] = 0;
                } else arr[i][j] = 1;
            }
        }

        visited[r - 1][0] = true;
        dfs(r - 1, 0, 1);

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int depth) {
        //목적지인 경우
        if (x == 0 && y == c - 1) {
            if(depth == k)
                answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < r && ny >= 0 && ny < c && arr[nx][ny] != 1 && !visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1);
                visited[nx][ny] = false;
            }
        }
    }
}
