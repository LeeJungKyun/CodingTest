import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int[] counts = new int[10000001];
        ArrayList<Integer> count = new ArrayList<>();
        
        for(int num : tangerine){
            counts[num]++;
        }
        
        for(int i = 1; i < 10000001; i++){
            if(counts[i] == 0) continue;
            count.add(counts[i]);
        }
        
        Collections.sort(count, Collections.reverseOrder());
        
        int sum = 0;
        for(int num : count){
            if(sum + num >= k){
                answer++;
                break;
            } else{
                sum += num;
                answer++;
            }
        }
        
        
        return answer;
    }
}