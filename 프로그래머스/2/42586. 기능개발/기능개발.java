import java.util.*;

class Solution{
    public int[] solution(int[] progresses , int[] speeds){
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i<progresses.length; i++){
            int days = (int) Math.ceil( (100.0 - progresses[i]) / speeds[i]);
            queue.offer(days);
        }

        List<Integer> releaseFunction = new ArrayList<>();
        while(!queue.isEmpty()){ 
            int releaseDay = queue.poll();
            int releaseFunctionCount = 1; 

            while(!queue.isEmpty() && queue.peek() <= releaseDay){
                queue.poll();
                releaseFunctionCount++;
            }
            releaseFunction.add(releaseFunctionCount);
        }
        return releaseFunction.stream().mapToInt(a -> a).toArray();
    }
}