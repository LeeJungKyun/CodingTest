import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int n, m;
  static int[] arr;
  static int maxGap = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());

    //가로등이 1개일 경우
    if (m == 1) {
      int point = Integer.parseInt(br.readLine());
      System.out.println(Math.max(point, n - point));
      return;
    }

    arr = new int[m];

    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < m; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    maxGap = Math.max(maxGap, arr[0]);

    for (int i = 1; i < m; i++) {
      int gap = (arr[i] - arr[i - 1] + 1) / 2;
      maxGap = Math.max(maxGap, gap);
    }

    maxGap = Math.max(maxGap, n - arr[m - 1]);

    System.out.println(maxGap);
  }
}
