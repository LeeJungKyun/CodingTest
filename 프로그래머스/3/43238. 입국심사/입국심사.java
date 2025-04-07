import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long start = 0;
        //최대 걸리는 시간
        long end = (long) n * times[times.length - 1];
        long mid, sum;
        while(start <= end){
            mid = (start + end) / 2;
            sum = 0;
            
            for(int i = 0; i < times.length; i++){
                sum += mid / times[i];
            }
            
            if(n > sum){
                start = mid + 1;
            } else{
                end = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}