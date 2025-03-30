import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Usado{
  int node;
  int weight;

  public Usado(int node, int weight) {
    this.node = node;
    this.weight = weight;
  }
}
public class Main {
  static int n, q;
  static List<Usado>[] nodes;
  static int INF = Integer.MAX_VALUE;
  static int k, v;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    n = Integer.parseInt(st.nextToken());
    q = Integer.parseInt(st.nextToken());

    nodes = new ArrayList[n + 1];

    for (int i = 1; i <= n; i++) {
      nodes[i] = new ArrayList<>();
    }

    // 노드 정보 입력
    for (int i = 0; i < n - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      nodes[start].add(new Usado(end, weight));
      nodes[end].add(new Usado(start, weight));
    }

    //쿼리
    for (int i = 0; i < q; i++) {
      st = new StringTokenizer(br.readLine());

      k = Integer.parseInt(st.nextToken());
      v = Integer.parseInt(st.nextToken());

      boolean[] visited = new boolean[n + 1];
      visited[v] = true;
      Queue<Integer> queue = new LinkedList<>();
      queue.add(v);

      int answer = 0;
      while (!queue.isEmpty()) {
        int cur = queue.poll();

        for (Usado usado : nodes[cur]) {
          if (!visited[usado.node] && usado.weight >= k) {
            queue.add(usado.node);
            visited[usado.node] = true;
            answer++;
          }
        }
      }
      sb.append(answer).append('\n');
    }
    System.out.println(sb);
  }
}
