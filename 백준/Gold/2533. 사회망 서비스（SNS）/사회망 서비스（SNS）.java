import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    int n;
    Node next;

    public Node(int n, Node next) {
        this.n = n;
        this.next = next;
    }
}
public class Main {
    static int n;
    static boolean[] visited;
    static Node[] tree;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        tree = new Node[n + 1];

        StringTokenizer st;

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            tree[start] = new Node(end, tree[start]);
            tree[end] = new Node(start, tree[end]);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int num) {
        visited[num] = true;
        dp[num][0] = 0;
        dp[num][1] = 1;

        for (Node next = tree[num]; next != null; next = next.next) {
            if (!visited[next.n]) {
                dfs(next.n);
                dp[num][0] += dp[next.n][1];
                dp[num][1] += Math.min(dp[next.n][0], dp[next.n][1]);
            }
        }
    }
}
