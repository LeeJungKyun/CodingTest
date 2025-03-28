import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            //열린괄호라면
            if(s.charAt(i) == '('){
                num++;
            }
            else{
                num--;
            }
            
            //열린 괄호보다 닫힌 괄호가 더 많으면
            if(num < 0){
                answer = false;
                break;
            }
        }
        
        if(num == 0){
            answer = true;
        } else answer = false;
        return answer;
    }
}