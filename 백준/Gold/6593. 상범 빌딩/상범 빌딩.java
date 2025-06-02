import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int z, x, y, time;

        public Point(int z, int x, int y, int time) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int l, r, c;
    static int[][][] map;
    static boolean[][][] visited;
    static Point start, end;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0)
                break;

            map = new int[l][r][c];
            visited = new boolean[l][r][c];

            for (int k = 0; k < l; k++) {
                for (int i = 0; i < r; i++) {
                    String s = br.readLine();
                    for (int j = 0; j < c; j++) {
                        char ch = s.charAt(j);
                        if (ch == 'S') {
                            start = new Point(k, i, j, 0);
                            map[k][i][j] = 0;
                        } else if (ch == 'E') {
                            end = new Point(k, i, j, 0);
                            map[k][i][j] = 0;
                        } else if (ch == '#') {
                            map[k][i][j] = 1;
                        } else {
                            map[k][i][j] = 0;
                        }
                    }
                }
                br.readLine();
            }

            Queue<Point> queue = new LinkedList<>();
            queue.offer(start);
            boolean flag = false;

            while(!queue.isEmpty()){
                Point cur = queue.poll();
                for (int i = 0; i < 6; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    int nz = cur.z + dz[i];

                    if(nx == end.x && ny == end.y && nz == end.z){
                        flag = true;
                        sb.append("Escaped in ").append(cur.time + 1).append(" minute(s).").append('\n');
                        break;
                    }

                    if (nx >= 0 && nx < r && ny >= 0 && ny < c && nz >= 0 && nz < l && !visited[nz][nx][ny] && map[nz][nx][ny] != 1) {
                        visited[nz][nx][ny] = true;
                        queue.add(new Point(nz, nx, ny, cur.time + 1));
                    }
                }
                if(flag)
                    break;
            }
            if(!flag){
                sb.append("Trapped!").append('\n');
            }
        }

        System.out.println(sb);
    }

    public static void printArr(int[][][] map) {
        for (int k = 0; k < l; k++) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    System.out.print(map[k][i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}