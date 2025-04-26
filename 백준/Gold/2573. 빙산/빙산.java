import java.util.*;
import java.io.*;

public class Main {

  static int n, m;
  static int[][] arr;
  static int[][] decrease;
  static boolean[][] visited;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, -1, 0, 1};
  static int result = 0;
  static Queue<int[]> queue;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    while (true) {
      int count = countIce();
      if (count == 0) {
        System.out.println(0);
        return;
      }
      if (count >= 2) {
        System.out.println(result);
        return;
      }
      decrease = new int[n][m];
      // 물(0)인 곳들에서 주변을 확인
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (arr[i][j] == 0) {
            for (int dir = 0; dir < 4; dir++) {
              int nx = i + dx[dir];
              int ny = j + dy[dir];
              if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
              }
              if (arr[nx][ny] > 0) {
                decrease[nx][ny]++;
              }
            }
          }
        }
      }

      // 한 번에 빙산 줄이기
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          arr[i][j] -= decrease[i][j];
          if (arr[i][j] < 0) {
            arr[i][j] = 0;
          }
        }
      }
      result++;
    }
  }

  // ---------- 함수
  public static int countIce() {
    visited = new boolean[n][m];
    int count = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (arr[i][j] > 0 && !visited[i][j]) {
          bfs(i, j, visited);
          count++;
        }
      }
    }
    return count;
  }

  public static void bfs(int x, int y, boolean[][] visited) {
    queue = new LinkedList<>();
    queue.add(new int[]{x, y});
    visited[x][y] = true;

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int curX = cur[0], curY = cur[1];
      for (int dir = 0; dir < 4; dir++) {
        int nx = curX + dx[dir];
        int ny = curY + dy[dir];
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
        if (arr[nx][ny] > 0 && !visited[nx][ny]) {
          visited[nx][ny] = true;
          queue.add(new int[]{nx, ny});
        }
      }
    }
  }
}
