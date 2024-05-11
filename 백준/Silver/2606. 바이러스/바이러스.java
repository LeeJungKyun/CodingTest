import java.io.*;
import java.util.*;

public class Main{

    static int[][] graph;
    static LinkedList<Integer>[] adjList;
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

        //인접행렬
        graph = new int[n+1][n+1];
        //인접리스트
        adjList = new LinkedList[n+1];
        visited = new boolean[n + 1];

        for(int i = 0; i <= n; i++){
            visited[i] = false;
            adjList[i] = new LinkedList<>();
            for(int j = 0; j <= n; j++){
                graph[i][j] = 0;

            }
        }
        for(int i = 0; i < p; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //인접리스트 초기화
            graph[a][b] = graph[b][a] = 1;
            //인접행렬 초기화
            adjList[a].add(b);
            adjList[b].add(a);
        }
        dfs_2(1);
        System.out.println(result);
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

    public static void dfs_2(int start){
        visited[start] = true;
        for(int i : adjList[start]){
            if(!visited[i]){
                result++;
                dfs_2(i);
            }
        }
    }
}