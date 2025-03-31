import java.util.*;

class Edge implements Comparable<Edge> {
    int dest;
    int weight;
    
    public Edge(int dest, int weight){
        this.dest = dest;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge o){
        return this.weight - o.weight;
    }
}

class Solution {
    final static int INF = Integer.MAX_VALUE;
    public int solution(int n, int[][] road, int k) {
        int answer = 0;
        ArrayList<Edge>[] graph;
        int[] dist = new int[n + 1];
        graph = new ArrayList[n + 1];
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }
        
        for(int[] arr : road){
            int start = arr[0];
            int end = arr[1];
            int weight = arr[2];
            
            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start, weight));
            
        }
        
        pq.add(new Edge(1, 0));
        dist[1] = 0;
        
        while(!pq.isEmpty()){
            //현재 방문 노드의 정보 poll
            Edge cur = pq.poll();
            

            int curNode = cur.dest;
            int curWeight = cur.weight;
            
            if(dist[curNode] < curWeight){
                continue;
            }
            
            for(Edge edge : graph[curNode]){
                int nextNode = edge.dest;
                int nextWeight = curWeight + edge.weight;
                
                if(dist[nextNode] > nextWeight){
                    dist[nextNode] = nextWeight;
                    pq.add(new Edge(nextNode, nextWeight));
                }
            }
        }
        
        for(int i = 1; i <= n; i++){
            if(dist[i] <= k)
                answer++;
        }
        
        return answer;
    }
}