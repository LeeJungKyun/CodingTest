import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        Arrays.sort(arr);
        
        for(int i = 0; i < arr.length; i++){
            int h = arr.length - i;
            
            if(arr[i] >= h){
                answer = h;
                break;
            }
        }
        return answer;
    }
}