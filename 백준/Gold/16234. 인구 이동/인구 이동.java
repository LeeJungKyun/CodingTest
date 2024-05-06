import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    static int[][] map;
    static boolean[][] visited;
    static Queue<Pair> q;
    static ArrayList<Pair> list;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int n;
    static int l;
    static int r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n][n];

        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = false;
            }
        }
        System.out.println(move());
    }

    public static int bfs(int x, int y) {
        q = new LinkedList<>();
        list = new ArrayList<>();

        q.add(new Pair(x, y));
        list.add(new Pair(x, y));

        visited[x][y] = true;
        int sum = map[x][y];

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = pair.x + dx[dir];
                int ny = pair.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (visited[nx][ny] || map[nx][ny] == 0)
                    continue;
                int diff = Math.abs(map[nx][ny] - map[pair.x][pair.y]);
                if (diff >= l && diff <= r) {
                    visited[nx][ny] = true;
                    q.add(new Pair(nx, ny));
                    list.add(new Pair(nx, ny));
                    sum += map[nx][ny];
                }
            }
        }
        return sum;
    }
    public static void calculate(int sum){
        int avg = sum / list.size();
        for (Pair p : list) {
            map[p.x][p.y] = avg;
        }
    }
    public static int move(){
        int result = 0;
        while(true){
            boolean isMoved = false;
            visited = new boolean[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(i, j);
                        if (list.size() > 1) {
                            calculate(sum);
                            isMoved = true;
                        }
                    }
                }
            }
            if(!isMoved)
                return result;
            result++;
        }
    }
}
