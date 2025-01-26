import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int n;
    static List<Integer>[] nodes;
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());

            nodes = new List[n + 1];
            visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                nodes[i] = new ArrayList<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                nodes[i].add(Integer.parseInt(st.nextToken()));
            }

            result = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                    result++;
                }
            }
            System.out.println(result);
        }

    }

    public static void dfs(int cur) {
        visited[cur] = true;
        for (Integer i : nodes[cur]) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}
