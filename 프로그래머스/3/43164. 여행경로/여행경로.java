import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    LinkedList<String> route = new LinkedList<>();
    
    public String[] solution(String[][] tickets) {
        for (String[] ticket : tickets) {
            map.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }
        
        findRoute("ICN");
        
        return route.toArray(new String[0]);
    }
    
    public void findRoute(String departure){
        PriorityQueue<String> arrivals = map.get(departure);
        while(arrivals != null && ! arrivals.isEmpty()){
            String next = arrivals.poll();
            findRoute(next);
        }
        
        route.addFirst(departure);
    }
}