import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer>[] list;
    static int n;
    static int[] cost;
    static int[] indegree;
    static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        list = new ArrayList[n + 1];
        indegree = new int[n + 1];
        times = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[i] = new ArrayList<>();
            times[i] = Integer.parseInt(st.nextToken());
            indegree[i] = Integer.parseInt(st.nextToken());

            for (int j = 0; j < indegree[i]; j++) {
                list[Integer.parseInt(st.nextToken())].add(i);
            }
        }

        cost = new int[n + 1];
        topologySort();

        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, cost[i]);
        }
        System.out.println(result);
    }

    public static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                cost[i] = times[i];
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int i = 0; i < list[current].size(); i++) {
                int next = list[current].get(i);
                cost[next] = Math.max(cost[current] + times[next], cost[next]);
                indegree[next]--;
                if (indegree[next] == 0) q.offer(next);
            }
        }
    }
}
