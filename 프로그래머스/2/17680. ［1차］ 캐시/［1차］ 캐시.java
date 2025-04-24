import java.util.*;

class Solution {
    static final int CACHE_HIT = 1;
    static final int CACHE_MISS = 5;
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize == 0){
            return CACHE_MISS * cities.length;
        }
        Deque<String> deque = new ArrayDeque<>();
        for(String city : cities){
        String normalizeCity = city.toUpperCase();
    
        // 캐시에 이미 존재할 경우 (HIT)
        if(deque.contains(normalizeCity)){
            deque.remove(normalizeCity);
            deque.addFirst(normalizeCity);
            answer += CACHE_HIT;
        } else {
            // 캐시에 없으면 (MISS)
            if(deque.size() == cacheSize){
                deque.removeLast();
            }
            deque.addFirst(normalizeCity);
            answer += CACHE_MISS;
        }
    }
        return answer;
    }
}