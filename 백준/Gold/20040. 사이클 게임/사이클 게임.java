import java.io.*;
import java.util.*;

public class Main {

  static int[] parent;
  static int n, m;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      if (find(x) == find(y)) {
        System.out.println(i + 1);
        return;
      } else {
        union(x, y);
      }
    }
    System.out.println(0);
  }

  public static void union(int n1, int n2) {
    int p1 = find(n1);
    int p2 = find(n2);

    if (p1 < p2) {
      parent[p2] = p1;
    } else {
      parent[p1] = p2;
    }
  }

  public static int find(int n) {
    if (parent[n] == n) {
      return n;
    } else {
      return parent[n] = find(parent[n]);
    }
  }
}
