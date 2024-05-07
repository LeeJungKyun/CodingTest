import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static int count = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[n1][n2] = arr[n2][n1] = 1;
        }
        System.out.println(bfs(1));
    }

    public static int bfs(int k) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        visited[k] = true;
        while (!q.isEmpty()) {
            int temp = q.poll();
            for (int i = 1; i <= n; i++) {
                if (arr[temp][i] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        return count;
    }
}