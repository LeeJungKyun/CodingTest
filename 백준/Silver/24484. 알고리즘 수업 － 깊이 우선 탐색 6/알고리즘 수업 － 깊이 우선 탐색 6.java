import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static ArrayList<Integer>[] nodes;
    static boolean[] visited;
    static int[] t;
    static int[] d;
    static int seq = 1;
    static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        t = new int[n + 1];
        d = new int[n + 1];

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
            Collections.sort(nodes[i], Collections.reverseOrder());
        }


        dfs(r, 0);

        for (int i = 1; i <= n; i++) {
            if(t[i] != 0)
                result += (long) t[i] * d[i];
        }

        System.out.println(result);
    }

    public static void dfs(int cur, int depth) {
        visited[cur] = true;
        d[cur] = depth;
        t[cur] = seq++;
        for (Integer next : nodes[cur]) {
            if (!visited[next]) {
                dfs(next, depth + 1);
            }
        }
    }
}
