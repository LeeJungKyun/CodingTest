import java.util.*;
import java.io.*;


public class Main {

  static int f, s, g, u, d;
  static int[] dist;
  static Queue<Integer> queue;
  static int count;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    queue = new LinkedList<>();

    f = Integer.parseInt(st.nextToken());
    s = Integer.parseInt(st.nextToken());
    g = Integer.parseInt(st.nextToken());
    u = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());

    dist = new int[f + 1];
    Arrays.fill(dist, -1);
    //시작점 추가
    queue.add(s);
    dist[s] = 0;

    while (!queue.isEmpty()) {
      int cur = queue.poll();
      if (cur == g) {
        System.out.println(dist[cur]);
        return;
      }
      int up = cur + u;
      int down = cur - d;
      for (int next : new int[]{up, down}) {
        if (next >= 1 && next <= f && dist[next] == -1) {
          dist[next] = dist[cur] + 1;
          queue.add(next);
        }
      }
    }
    System.out.println("use the stairs");
  }
}