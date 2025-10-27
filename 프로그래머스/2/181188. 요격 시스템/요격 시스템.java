import java.util.*;

class Solution {
    static final int SIZE = 100_000_001;
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int count = 0;
        
        for(int[] arr : targets){
            if(count <= arr[0]){
                count = arr[1];
                answer++;
            }
        }
        return answer;
    }
}