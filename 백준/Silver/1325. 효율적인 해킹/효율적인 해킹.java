import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] graph;
    static int[] result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // A B : A가 B를 신뢰 → B → A 방향 간선
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a); // b → a 로 저장 (역방향)
        }

        // 모든 노드에 대해 DFS 수행
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            result[i] = dfs(i);
        }

        int max = Arrays.stream(result).max().getAsInt();

        for (int i = 1; i <= n; i++) {
            if (result[i] == max) {
                System.out.print(i + " ");
            }
        }
    }

    static int dfs(int cur) {
        visited[cur] = true;
        int cnt = 1;

        for (int next : graph[cur]) {
            if (!visited[next]) {
                cnt += dfs(next);
            }
        }
        return cnt;
    }
}