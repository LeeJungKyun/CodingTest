import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int size = nums.length / 2;
        HashSet<Integer> set = new HashSet<>();
        for(Integer num : nums){
            set.add(num);
        }
        answer = Math.min(set.size(), size);
        return answer;
    }
}