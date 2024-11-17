import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int a, b;
    static int result = -1;
    static List<Integer>[] relation;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        relation = new ArrayList[n + 1];
        checked = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            relation[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            relation[parent].add(child);
            relation[child].add(parent);
        }

        dfs(a, b, 0);
        System.out.println(result);

    }

    static void dfs(int start, int end, int cnt) {
        if (start == end) {
            result = cnt;
            return;
        }

        checked[start] = true;
        for (int i = 0; i < relation[start].size(); i++) {
            int next = relation[start].get(i);
            if (!checked[next]) {
                dfs(next, end, cnt + 1);
            }
        }
    }

}

