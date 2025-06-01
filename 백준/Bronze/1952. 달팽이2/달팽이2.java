import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] map;

    static int[] dx = {0, 1, 0, -1}; // 오른쪽으로, 아랫쪽으로, 왼쪽으로, 윗쪽으로
    static int[] dy = {1, 0, -1, 0};

    static boolean[][] visited; // 방문 처리

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();
        sc.close();

        map = new int[m][n];
        visited = new boolean[m][n];

        int cnt = 0;
        int sum = 0;

        int idx = 0;
        int curX = 0;
        int curY = 0;
        visited[curX][curY] = true;
        sum = 1;

        while(true) {
            int nx = curX + dx[idx]; // 탐색한 새로운 좌표
            int ny = curY + dy[idx];

            // 모든 칸을 지나왔다면 답 출력하고 종료
            if(sum == n * m) {
                System.out.println(cnt);
                return;
            }

            // 새로 탐색한 좌표가 범위 안에 있고 아직 방문하지 않은 곳이라면
            if(nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
                sum++;
                visited[nx][ny] = true;
                curX = nx;
                curY = ny;
            }
            else {
                idx++;
                cnt++;
            }

            if(idx >= 4) {
                idx = 0;
            }
        }

    }

}