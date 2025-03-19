import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Deque<int[]> deque;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        deque = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("1 ");
        int in = arr[0];

        for (int i = 1; i < n; i++) {
            deque.add(new int[]{(i + 1), arr[i]});
        }

        while (!deque.isEmpty()) {
            if (in > 0) {
                for (int i = 1; i < in; i++) {
                    deque.add(deque.poll());
                }

                int[] next = deque.poll();
                in = next[1];
                sb.append(next[0] + " ");
            } else {
                for (int i = 1; i < -in; i++) {
                    deque.addFirst(deque.pollLast());
                }
                int[] next = deque.pollLast();
                in = next[1];
                sb.append(next[0] + " ");
            }
        }
        System.out.println(sb);
    }
}
