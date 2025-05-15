import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int n, d;
    static List<List<Node>> graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        //초기 그래프와 거리 배열 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= 100001; i++) {
            graph.add(new ArrayList<>());
        }
        dist = new int[10001];
        for (int i = 0; i < dist.length; i++) dist[i] = i;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, weight));
        }

        dijkstra(0);

        System.out.println(dist[d]);
    }

    public static void dijkstra(int cur) {
        //범위를 벗어나면 끝
        if (cur > d) return;

        //다음칸으로 그냥 갔을 때 vs 지름길로 도착을 했을수도 있을때의 거리
        if (dist[cur + 1] > dist[cur] + 1)
            dist[cur + 1] = dist[cur] + 1;

        //현재 위치에서의 지름길 확인
        for (Node node : graph.get(cur)) {
            if (dist[cur] + node.weight < dist[node.node]) {
                dist[node.node] = dist[cur] + node.weight;
            }
        }

        dijkstra(cur + 1);
    }
}
