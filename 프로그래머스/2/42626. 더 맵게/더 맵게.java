import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s : scoville){
            pq.add(s);
        }
        
        while(pq.size() >= 2 && pq.peek() < K){
            //가장 맵지 않은 음식
            int first = pq.poll();
            //두 번째로 맵지 않은 음식
            int second = pq.poll();
        
            int mixed = first + (second * 2);
            
            pq.add(mixed);
            answer++;
        }
        
        if(pq.peek() < K)
            return -1;
        
        return answer;
    }
}