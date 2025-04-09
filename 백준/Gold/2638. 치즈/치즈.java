import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static int cheeseCount;
  static int[][] arr;
  static boolean[][] visited;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, -1, 0, 1};
  static int[][] tempArr;
  static Queue<int[]> queue;
  //걸리는 시간
  static int time;

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
        if (arr[i][j] == 1) cheeseCount++;
      }
    }

    //치즈가 다 녹아 없어질 때까지 반복
    while (cheeseCount > 0) {
      // tempArr에 arr 복사
      tempArr = new int[n][m];
      for (int i = 0; i < n; i++) {
        tempArr[i] = arr[i].clone();
      }

      // 0,0 은 항상 외부이므로 해당 칸에서 시작하는 곳들은 tempArr에서 다 3으로 초기화
      bfs(0,0);

      //없어질 치즈 확인 후 삭제
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          //치즈인 경우
          if (arr[i][j] == 1) {
            int airCount = 0;
            for (int dir = 0; dir < 4; dir++) {
              int nx = i + dx[dir];
              int ny = j + dy[dir];
              if (nx >= 0 && ny >= 0 && nx < n && ny < m && tempArr[nx][ny] == 3) {
                airCount++;
              }
            }
            if (airCount >= 2) {
              arr[i][j] = 0;
              cheeseCount--;
            }
          }
        }
      }
      time++;
    }
    System.out.println(time);
  }

  public static void bfs(int x, int y) {
    queue = new LinkedList<>();
    queue.add(new int[]{x, y});
    arr[x][y] = 3;
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      for (int i = 0; i < 4; i++) {
        int nx = cur[0] + dx[i];
        int ny = cur[1] + dy[i];

        if (nx >= 0 && ny >= 0 && nx < n && ny < m && tempArr[nx][ny] == 0) {
          //방문했다는 표시로 3으로 초기화
          tempArr[nx][ny] = 3;
          queue.add(new int[]{nx, ny});
        }
      }
    }
  }
}
