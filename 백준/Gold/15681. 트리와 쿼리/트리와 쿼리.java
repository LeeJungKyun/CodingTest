import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, r, q;
    static int[] qArr, cnt;
    static ArrayList<Integer>[] tree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        cnt = new int[n + 1];

        tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            tree[x].add(y);
            tree[y].add(x);
        }

        qArr = new int[q];

        for (int i = 0; i < q; i++)
            qArr[i] = Integer.parseInt(br.readLine());

        dfs(r, -1);

        for (int num : qArr) {
            sb.append(cnt[num]).append('\n');
        }
        System.out.println(sb);

    }

    private static void dfs(int x, int prevNode) {
        cnt[x] = 1;

        for (Integer cur : tree[x]) {
            if(cur == prevNode)
                continue;
            dfs(cur, x);
            cnt[x] += cnt[cur];
        }
    }
}
