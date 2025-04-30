import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>();
        int count = 0;
        
        String prev = "prev";

        for(String word : words){
            if(count == 0){
                prev = word;
                set.add(word);
                count++;
                continue;
            }
            
            if(!set.contains(word) && prev.charAt(prev.length() - 1) == word.charAt(0)){
                set.add(word);
                prev = word;
                count++;
            }
            else{
                break;
            }
        }
        
        System.out.println(count);
        
        if(count == words.length){
            answer[0] = 0;
            answer[1] = 0;
            return answer;
        }
        
        int num = count % n + 1;
        int turn = count / n + 1;
        
        answer[0] = num;
        answer[1] = turn;

        return answer;
    }
}