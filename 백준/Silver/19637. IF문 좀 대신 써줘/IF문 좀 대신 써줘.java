import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static String[] status;
  static int[] power;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    status = new String[n];
    power = new int[n];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      status[i] = st.nextToken();
      power[i] = Integer.parseInt(st.nextToken());
    }

    StringBuilder sb = new StringBuilder();



    while (m-- > 0) {
      int num = Integer.parseInt(br.readLine());

      int left = 0;
      int right = n - 1;

      while (left <= right) {
        int mid = (left + right) / 2;

        if (power[mid] < num) {
          left = mid + 1;
        }
        else right = mid - 1;
      }
      sb.append(status[left]).append('\n');
    }
    System.out.print(sb);
  }
}
