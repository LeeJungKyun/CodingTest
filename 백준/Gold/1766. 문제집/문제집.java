import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] indegree;
    static ArrayList<Integer>[] list;
    static PriorityQueue<Integer> pq;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        list = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            indegree[b]++;
        }

        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 1; i <= n; i++) {
            if(indegree[i] == 0)
                pq.offer(i);
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for (int next : list[cur]) {
                indegree[next]--;
                if(indegree[next] == 0)
                    pq.add(next);
            }
        }

        System.out.println(sb);
    }
}
