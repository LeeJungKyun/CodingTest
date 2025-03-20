import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(computers, visited, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void bfs(int[][] computers, boolean[] visited, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        
        while(!queue.isEmpty()){
            int current = queue.poll();
            
            for(int i = 0; i < computers.length; i++){
                if(!visited[i] && computers[current][i] == 1){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}