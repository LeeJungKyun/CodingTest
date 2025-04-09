import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        //가격들에 대해서
        for(int i = 0; i < n; i++){
            //스택의 마지막 값이 현재 위치의 값보다 작으면 그 인덱스의 값은 이제 가격이 떨어짐
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            //인덱스만 저장해놓기
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            int idx = stack.pop();
            answer[idx] = n - idx - 1;
        }
        return answer;
    }
}