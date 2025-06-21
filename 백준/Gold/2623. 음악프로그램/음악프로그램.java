import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] graph;
    static int[] indegree;
    static Queue<Integer> queue;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for (int j = 1; j < num; j++) {
                int cur = Integer.parseInt(st.nextToken());
                graph[before].add(cur);
                indegree[cur]++;
                before = cur;
            }
        }

        queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if(indegree[i] == 0)
                queue.offer(i);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            sb.append(curr).append(" ");
            count++;

            for (int next : graph[curr]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        System.out.println(count == n ? sb : 0);
    }
}
