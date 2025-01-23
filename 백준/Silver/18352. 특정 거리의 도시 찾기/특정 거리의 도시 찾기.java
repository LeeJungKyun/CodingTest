import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;
import java.util.function.IntFunction;

public class Main {
    static int n, m, k, x;
    static int[] dist;
    static List<Integer>[] nodes;
    static final int INF = -1;
    static ArrayList<Integer> result = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        //거리 정보
        k = Integer.parseInt(st.nextToken());
        //출발 도시의 번호
        x = Integer.parseInt(st.nextToken());

        nodes = new List[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, INF);

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
        }

        queue.add(x);
        dist[x] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (dist[cur] > k) {
                break;
            }
            if (dist[cur] == k) {
                result.add(cur);
            }

            for (int next : nodes[cur]) {
                if (dist[next] != INF) {
                    continue;
                }
                dist[next] = dist[cur] + 1;
                queue.add(next);
            }
        }
        
        Collections.sort(result);

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (Integer i : result) {
                sb.append(i).append('\n');
            }
        }
        System.out.println(sb);
    }
}
