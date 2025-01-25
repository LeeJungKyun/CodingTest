import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static List<Integer>[] nodes;
    static boolean[] visited;
    static int[] result;
    static int index = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());


        nodes = new List[n + 1];
        visited = new boolean[n + 1];
        result = new int[n + 1];


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

        dfs(r);

        for (int i = 1; i <= n; i++) {
            System.out.println(result[i]);
        }
    }

    public static void dfs(int cur) {
        visited[cur] = true;
        result[cur] = index++;

        for (Integer val : nodes[cur]) {
            if (!visited[val]) {
                dfs(val);
            }
        }
    }
}
