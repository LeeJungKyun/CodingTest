import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        String[] parts = s.split("},\\{");
        
        Arrays.sort(parts, Comparator.comparingInt(String::length));
        
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for(String part : parts){
            for(String numStr : part.split(",")){
                int num = Integer.parseInt(numStr);
                set.add(num);
            }
        }
        
        int[] answer = new int[set.size()];
        int idx = 0;
        for(int num : set){
            answer[idx++] = num;
        }
        return answer;
    }
}