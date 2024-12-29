import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n;
    static char[][] arr;
    static char[] mobis = new char[]{'O', 'B', 'I', 'S'};
    static Queue<Point> queue;
    static int cnt;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'M') {
                    queue.add(new Point(i, j));
                }
            }
        }

        //구현 로직
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int dir = 0; dir < 8; dir++) {
                int nx = cur.x + dx[dir] * 4;
                int ny = cur.y + dy[dir] * 4;

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                int x = cur.x + dx[dir];
                int y = cur.y + dy[dir];

                for (int i = 0; i < 4; i++) {
                    if(arr[x][y] != mobis[i]) break;
                    x += dx[dir];
                    y += dy[dir];
                    if (i == 3) {
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
