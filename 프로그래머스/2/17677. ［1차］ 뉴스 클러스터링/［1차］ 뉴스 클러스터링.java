import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        
        //표준화
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        //배열 만들기 (str1)
        for(int i = 0; i < str1.length() - 1; i++){
            char a = str1.charAt(i);
            char b = str1.charAt(i + 1);
            if(a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z'){
                String pair = "" + a + b;
                map1.put(pair, map1.getOrDefault(pair, 0) + 1);
            }
        }

        //배열 만들기 (str2)
        for(int i = 0; i < str2.length() - 1; i++){
            char a = str2.charAt(i);
            char b = str2.charAt(i + 1);
            if(a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z'){
                String pair = "" + a + b;
                map2.put(pair, map2.getOrDefault(pair, 0) + 1);
            }
        }
        
        
        int intersection = 0;
        int union = 0;
        
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        
        for(String key : keys) {
            int cnt1 = map1.getOrDefault(key, 0);
            int cnt2 = map2.getOrDefault(key, 0);
            intersection += Math.min(cnt1, cnt2);
            union += Math.max(cnt1, cnt2);
        }
        
        
        if(union == 0) return 65536;
        else return (int)( (double) intersection / union * 65536);
    }
}