import java.util.*;


class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i = 0; i < priorities.length; i++){
            queue.add(new int[]{i, priorities[i]});
        }
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            
            boolean hasHigher = false;
            
            for(int[] doc : queue){
                if(doc[1] > current[1]){
                    hasHigher = true;
                    break;
                }
            }
            
            if(hasHigher){
                queue.add(current);
            } else{
                answer++;
                if(current[0] == location)
                    return answer;
            }
        }
        return answer;
    }
}