import java.io.*;
import java.util.*;

public class Main{

    static int[][] graph;
    static int result = 0;
    static boolean[] visited;
    static int n;
    static int p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];
        visited = new boolean[n + 1];

        for(int i = 0; i <= n; i++){
            visited[i] = false;
            for(int j = 0; j <= n; j++){
                graph[i][j] = 0;

            }
        }
        for(int i = 0; i < p; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = 1;
        }

        dfs(1);
        System.out.println(result - 1);
    }

    public static void dfs(int start){
        visited[start] = true;
        result++;
        for(int i = 0; i <= n; i++){
            if(graph[start][i] == 1 && !visited[i]){
                dfs(i);
            }
        }
    }
}