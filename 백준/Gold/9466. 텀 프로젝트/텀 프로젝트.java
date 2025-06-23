import java.io.*;
import java.util.*;

public class Main {
    static int testCase;
    static int[] arr;
    static boolean[] visited, finished;
    static int n, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];

            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if(!visited[i])
                    dfs(i);
            }

            System.out.println(n - count);
        }
    }

    public static void dfs(int cur) {
        visited[cur] = true;
        int next = arr[cur];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            for(int i = next; i != cur; i = arr[i])
                count++;
            count++;
        }
        finished[cur] = true;
    }
}
