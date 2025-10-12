import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 1;
        int right = distance;
        while(left <= right){
            int mid = (left + right) / 2;
            
            if(removeRockCount(rocks, mid, distance) <= n){
                answer = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return answer;
    }
    
    public int removeRockCount(int[] rocks, int mid, int distance){
        int before = 0;
        int end = distance;
        
        int removeCount = 0;
        for(int i = 0; i < rocks.length; i++){
            if(rocks[i] - before < mid){
                removeCount++;
                continue;
            }
            before = rocks[i];
        }
        
        if(end - before < mid) removeCount++;
        return removeCount;
    }
}