import java.util.*;
import java.io.*;

public class Main {
  static int T;
  static int v, e;
  static List<List<Integer>> graph;
  static int[] colors;
  static final int RED = 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      v = Integer.parseInt(st.nextToken());
      e = Integer.parseInt(st.nextToken());

      graph = new ArrayList<>();
      colors = new int[v + 1];

      for (int vertex = 0; vertex <= v; vertex++) {
        graph.add(new ArrayList<>());
      }

      for (int edge = 0; edge < e; edge++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        graph.get(from).add(to);
        graph.get(to).add(from);
      }

      boolean flag = false;

      for (int vertex = 1; vertex <= v; vertex++) {
        if (colors[vertex] == 0) {
          flag = isBinary(vertex, RED);
          if(!flag) break;
        }
      }
      System.out.println(flag ? "YES" : "NO");
    }
  }

  public static boolean isBinary(int start, int color) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);

    colors[start] = color;

    while (!queue.isEmpty()) {
      int cur = queue.poll();
      for (int next : graph.get(cur)) {
        if (colors[cur] == colors[next]) {
          return false;
        }

        if (colors[next] == 0) {
          colors[next] = colors[cur] * -1;
          queue.add(next);
        }
      }
    }
    return true;
  }
}
