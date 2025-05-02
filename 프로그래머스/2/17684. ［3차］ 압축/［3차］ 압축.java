import java.util.*;

class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> list = new ArrayList<>();
        int curIndex;
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        // "A" ~ "Z"까지 String으로 직접 생성해서 저장
        for (int i = 1; i <= 26; i++) {
            String ch = "" + (char)('A' + i - 1);  // i=1일 때 'A', ..., i=26일 때 'Z'
            map.put(ch, i);
        }
        
        curIndex = 27;
        int i = 0;
        
        while(i < msg.length()){
            int end = i + 1;
            while(end <= msg.length() && map.containsKey(msg.substring(i, end))){
                end++;
            }
            
            String matched = msg.substring(i, end - 1);
            list.add(map.get(matched));
            
            if(end <= msg.length()) {
                map.put(msg.substring(i, end), curIndex++);
            }
            
            i = end - 1;
        }
        
        
        int[] answer = new int[list.size()];
        
        for(int j = 0; j < list.size(); j++){
            answer[j] = list.get(j);
        }
        
        
        return answer;
    }
}