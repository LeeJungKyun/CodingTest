import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int n, k, w;
    static int[] time, result, inDegree;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            //건물 건설 소요 시간
            time = new int[n + 1];
            result = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            //규칙 입력
            list = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            inDegree = new int[n + 1];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list[x].add(y);
                inDegree[y]++;
            }

            w = Integer.parseInt(br.readLine());

            topologicalSort();

            System.out.println(result[w]);
        }
    }

    public static void topologicalSort() {
        int prev = 0;
        Queue<Integer> queue = new LinkedList<>();

        //indegree가 0이면 큐에 추가
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                result[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list[cur]) {
                result[next] = Math.max(result[next], result[cur] + time[next]);
                inDegree[next]--;
                if (inDegree[next] == 0)
                    queue.add(next);
            }
        }
    }
}
