import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] arr;
    static boolean[][] visited, finished;
    static int safeZone = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];
        finished = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char ch = str.charAt(j);
                if (ch == 'U')
                    arr[i][j] = 0;
                else if(ch == 'D')
                    arr[i][j] = 1;
                else if(ch == 'L')
                    arr[i][j] = 2;
                else if(ch == 'R')
                    arr[i][j] = 3;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j])
                    dfs(i, j);
            }
        }

        System.out.println(safeZone);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;

        int nx = x + dx[arr[x][y]];
        int ny = y + dy[arr[x][y]];

        if(!visited[nx][ny])
            dfs(nx, ny);
        else{
            if(!finished[nx][ny])
                safeZone++;
        }
        finished[x][y] = true;
    }
}