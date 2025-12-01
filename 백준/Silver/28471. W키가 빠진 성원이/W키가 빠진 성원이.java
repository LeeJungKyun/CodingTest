import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,1,0,-1,-1,1,0};

    static int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[y][x] = true;
        int cnt = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i=0; i<7; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=N || visited[ny][nx] || graph[ny][nx]=='#') continue;
                cnt++;
                q.offer(new int[] {nx, ny});
                visited[ny][nx] = true;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new char[N][N];
        visited = new boolean[N][N];

        int sx=0, sy=0;

        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<N; j++){
                graph[i][j] = s.charAt(j);
                if(graph[i][j]=='F'){
                    sx = j;
                    sy = i;
                }
            }
        }

        System.out.println(bfs(sx,sy));
    }
}