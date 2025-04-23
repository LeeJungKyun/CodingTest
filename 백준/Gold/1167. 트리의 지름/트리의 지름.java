import java.util.*;
import java.io.*;

public class Main {
  static int v;
  static ArrayList<int[]>[] graph;
  static boolean[] visited;
  static int maxDist;
  static int farthestNode;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    v = Integer.parseInt(br.readLine());
    graph = new ArrayList[v + 1];
    for (int i = 1; i <= v; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 1; i <= v; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int node = Integer.parseInt(st.nextToken());
      int dest;
      int weight;
      while ((dest = Integer.parseInt(st.nextToken())) != -1) {
        weight = Integer.parseInt(st.nextToken());
        graph[node].add(new int[]{dest, weight});
      }
    }
    visited = new boolean[v + 1];
    dfs(1,0);

    visited = new boolean[v + 1];
    maxDist = 0;
    dfs(farthestNode,0);

    System.out.println(maxDist);
  }

  public static void dfs(int node, int dist) {
    visited[node] = true;

    if (dist > maxDist) {
      maxDist = dist;
      farthestNode = node;
    }
    for (int[] next : graph[node]) {
      int nextNode = next[0];
      int weight = next[1];
      if (!visited[nextNode]) {
        dfs(nextNode, dist + weight);
      }
    }
  }
}
