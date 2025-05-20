import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < numbers.length; i++){
            //스택에 들어있는 수가 현재 들어있는 수보다 작으면
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){
                int idx = stack.pop();
                answer[idx] = numbers[i];
            }
            //인덱스 저장
            stack.push(i);
        }
        while(!stack.isEmpty()){
            answer[stack.pop()] = -1;
        }
        return answer;
    }
}