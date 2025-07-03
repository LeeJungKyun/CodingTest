import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        int x, y, time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int n, m;
    static int[][] arr;
    static int[][] tempArr;
    static boolean[][] visited;
    static boolean[] checkVirus;
    static int[] virusResult;
    static ArrayList<Point> virus = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        //바이러스 m개
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        tempArr = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2)
                    virus.add(new Point(i, j, 0));
            }
        }

        checkVirus = new boolean[virus.size()];
        virusResult = new int[m];
        backTrack(0, 0);

        System.out.println((min == Integer.MAX_VALUE) ? -1 : min);
    }

    //어떤 바이러스가 선택될지 backTracking 하는 함수
    public static void backTrack(int depth, int start) {
        if (depth == m) {
            bfs();
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            if (!checkVirus[i]) {
                virusResult[depth] = i;
                checkVirus[i] = true;
                backTrack(depth + 1, i + 1);
                checkVirus[i] = false;
            }
        }
    }

    public static void bfs(){
        int max = -1;
        copyArr();
        visited = new boolean[n][n];
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            queue.add(virus.get(virusResult[i]));
            tempArr[virus.get(virusResult[i]).x][virus.get(virusResult[i]).y] = 2;
        }

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            max = Math.max(max, cur.time);

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!visited[nx][ny] && tempArr[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        tempArr[nx][ny] = 2;
                        queue.add(new Point(nx, ny, cur.time + 1));
                    }
                }
            }
        }

        if(isPossible()){
            min = Math.min(min, max);
        }
    }

    public static void copyArr(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 2)
                    tempArr[i][j] = 0;
                else tempArr[i][j] = arr[i][j];
            }
        }
    }

    public static boolean isPossible() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(tempArr[i][j] == -0)
                    return false;
            }
        }
        return true;
    }
}
