import java.util.*;
import java.io.*;

public class Main {
  static int r, c, n;
  static char[][] init;
  static char[][] full;
  static char[][] after;
  static char[][] afterAfter;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());

    init = new char[r][c];
    full = new char[r][c];
    after = new char[r][c];
    afterAfter = new char[r][c];

    for (int i = 0; i < r; i++) {
      String line = br.readLine();
      for (int j = 0; j < c; j++) {
        init[i][j] = line.charAt(j);
        full[i][j] = 'O';
        after[i][j] = 'O';
        afterAfter[i][j] = 'O';
      }
    }

    //폭탄이 터지고 난 배열 초기화
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (init[i][j] == 'O') {
          after[i][j] = '.';
          for (int dir = 0; dir < 4; dir++) {
            int nx = i + dx[dir];
            int ny = j + dy[dir];
            if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
              after[nx][ny] = '.';
            }
          }
        }
      }
    }

    // after 기준으로 다시 폭발
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (after[i][j] == 'O') {
          afterAfter[i][j] = '.';
          for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
              afterAfter[nx][ny] = '.';
            }
          }
        }
      }
    }

    if (n == 1) {
      print(init);
    } else if (n % 2 == 0) {
      print(full);
    } else if ((n - 3) % 4 == 0) {
      print(after);
    } else print(afterAfter);
  }

  public static void print(char[][] board) {
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }
}
