import java.util.*;
import java.io.*;

public class Main {
    static List<Integer>[] list;
    //사람의 수
    static int n;
    //친구 관계의 수
    static int m;
    static boolean[] visited;
    static int flag = 1;
    static int depth = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n];

        for(int i = 0; i < n; i++){
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        flag = 0;
        for(int i = 0; i < n; i++){
            visited = new boolean[n];
            dfs(i,1);
            if(flag == 1){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    public static void dfs(int start, int depth) {
        if(depth == 5){
            flag = 1;
            return;
        }
        visited[start] = true;
        for(int temp : list[start]){
            if(!visited[temp]){
                dfs(temp, depth + 1);
            }
        }
        visited[start] = false;
    }
}
