import java.io.*;
import java.util.*;

public class Main {
  static int n, c;
  static int[] arr;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());

    arr = new int[n];

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(arr);

    int left = 1;
    int right = arr[n - 1] - arr[0];
    int result = 0;

    while (left <= right) {
      int mid = (left + right) / 2;

      if (canInstall(mid)) {
        result = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    System.out.println(result);
  }

  public static boolean canInstall(int dist) {
    int cnt = 1;
    int last = arr[0];

    for (int i = 1; i < n; i++) {
      if (arr[i] - last >= dist) {
        cnt++;
        last = arr[i];
      }
    }
    return cnt >= c;
  }
}
