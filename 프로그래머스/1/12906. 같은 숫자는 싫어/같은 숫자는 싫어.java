import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        int back = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(Integer num : arr){
            if(stack.isEmpty()){
                stack.push(num);
                back = num;
            }
            else{
                if(num != back) {
                    stack.push(num);
                    back = num;
                }
            }
        }
        
        answer = new int[stack.size()];
        int n = stack.size();
        
        for(int i = n - 1; i >= 0; i--){
            answer[i] = stack.pop();
        }
        return answer;
    }
}