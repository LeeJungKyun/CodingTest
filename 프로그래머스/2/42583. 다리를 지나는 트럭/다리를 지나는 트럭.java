import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        //트럭 대기
        Queue<Integer> waiting = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();
        for(int i = 0; i < truck_weights.length; i++){
            waiting.add(truck_weights[i]);
        }
        
        for(int i = 0; i < bridge_length; i++){
            bridge.add(0);
        }
        
        int totalWeight = 0;
        
        while(!waiting.isEmpty() || totalWeight > 0){
            answer++;
            totalWeight -= bridge.poll();
            if(!waiting.isEmpty()){
                int nextWeight = waiting.peek();
                if(totalWeight + nextWeight <= weight){
                    waiting.poll();
                    bridge.add(nextWeight);
                    totalWeight += nextWeight;
                } else {
                    bridge.add(0);
                }
            } else bridge.add(0);
        }
        return answer;
    }
}