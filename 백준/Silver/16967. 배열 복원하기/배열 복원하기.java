import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int h, w, x, y;
  static int[][] arr;
  static int[][] origin;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    h = Integer.parseInt(st.nextToken());
    w = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    y = Integer.parseInt(st.nextToken());

    int height = h + x;
    int width = w + y;

    arr = new int[height][width];
    origin = new int[h][w];

    //큰 배열 입력
    for (int i = 0; i < height; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < width; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        origin[i][j] = arr[i][j];
      }
    }

    for (int i = x; i < h; i++) {
      for (int j = y; j < w; j++) {
        origin[i][j] = arr[i][j] - origin[i - x][j - y];
      }
    }

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        sb.append(origin[i][j]).append(' ');
      }
      sb.append('\n');
    }

    System.out.println(sb);
  }
}
