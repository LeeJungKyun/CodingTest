import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] weight;
    static boolean[] visited;
    static long result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        weight = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            visited[i] = true;
            dfs(i, i, 0);
        }
        System.out.println(result);
    }

    public static void dfs(int start, int cur, long cost) {
        if (allVisited()) {
            if (weight[cur][start] != 0) {
                result = Math.min(result, cost + weight[cur][0]);
            }
            return;
        }

        for (int i = 1; i < n; i++) {
            //아직 방문하지 않은 마을이고 거리가 0이 아니라면
            if(!visited[i] && weight[cur][i] != 0){
                visited[i] = true;
                dfs(start, i, cost + weight[cur][i]);
                visited[i] = false;
            }
        }
    }

    public static boolean allVisited() {
        for (int i = 0; i < n; i++) {
            if(!visited[i])
                return false;
        }
        return true;
    }
}
