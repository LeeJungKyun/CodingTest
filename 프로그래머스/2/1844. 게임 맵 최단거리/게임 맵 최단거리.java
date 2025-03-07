import java.util.*;

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int answer = 0;
        visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            if(x == n - 1 && y == m - 1){
                return dist;
            }
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                    if(maps[nx][ny] == 1 && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }
        return -1;
    }
}