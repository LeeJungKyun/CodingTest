import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] time;
    static int resultTime = Integer.MAX_VALUE, resultCount;
    static int SIZE = 200000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        time = new int[SIZE + 1];

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs(n);

        sb.append(resultTime).append('\n').append(resultCount);
        System.out.println(sb);
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        time[start] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == k) {
                //처음 도착한 경우
                if (resultTime == Integer.MAX_VALUE) {
                    resultTime = time[cur] - 1;
                    resultCount = 1;
                }
                //한번 방문한 경우
                else if (time[cur] - 1 == resultTime) {
                    resultCount++;
                }
            }

            int[] nx = {cur - 1, cur + 1, cur * 2};

            for (int next : nx) {
                if (next < 0 || next > SIZE) continue;
                if (time[next] == 0 || time[next] == time[cur] + 1) {
                    queue.offer(next);
                    time[next] = time[cur] + 1;
                }
            }
        }
    }
}
