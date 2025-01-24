import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, r;
    static List<Integer>[] nodes;
    static Queue<Integer> queue;
    static int[] result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        nodes = new List[n + 1];
        result = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes[u].add(v);
            nodes[v].add(u);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(nodes[i]);
        }

        queue.add(r);
        visited[r] = true;
        int index = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result[cur] = index;
            index++;

            for (Integer i : nodes[cur]) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(result[i]);
        }
    }
}
