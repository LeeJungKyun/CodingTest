import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        s = s.toLowerCase();
        String[] words = s.split(" ", -1);
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > 0) {
                // 첫 글자만 대문자로, 나머지는 그대로
                words[i] = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            }
        }
        
        answer = String.join(" ", words);
        return answer;
    }
}