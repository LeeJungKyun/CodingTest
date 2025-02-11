import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static char[][] board;
    static boolean[][] visited;
    static Queue<Point> queue;
    static ArrayList<Character> alphabet;
    static int result;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        alphabet = new ArrayList<>();

        board = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        dfs(0, 0, 1);

        System.out.println(result);
    }

    public static void dfs(int x, int y, int depth) {
        alphabet.add(board[x][y]);
        result = Math.max(result, depth);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                if (!alphabet.contains(board[nx][ny])) {
                    dfs(nx, ny, depth + 1);
                }
            }
        }
        alphabet.remove(Character.valueOf(board[x][y]));
    }
}
