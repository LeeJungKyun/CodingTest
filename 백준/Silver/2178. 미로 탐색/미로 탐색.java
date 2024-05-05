import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] map;
    public static boolean[][] visited;
    static Queue<Pair> q;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                visited[i][j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]){
                    visited[i][j] = true;
                    q.add(new Pair(i, j));
                    while(!q.isEmpty()){
                        Pair pair = q.poll();
                        for(int dir = 0; dir < 4; dir++){
                            int nx = pair.x + dx[dir];
                            int ny = pair.y + dy[dir];
                            if(nx < 0 || nx >= n || ny < 0 || ny >=m)
                                continue;
                            if(visited[nx][ny] || map[nx][ny] == 0)
                                continue;
                            visited[nx][ny] = true;
                            q.add(new Pair(nx,ny));
                            map[nx][ny] = map[pair.x][pair.y] + 1;
                        }
                    }
                }
            }
        }
        System.out.println(map[n-1][m-1]);
    }
}
