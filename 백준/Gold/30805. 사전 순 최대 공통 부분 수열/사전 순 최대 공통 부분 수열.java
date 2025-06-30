import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] a, b;
    static LinkedHashSet<Integer> set = new LinkedHashSet<>();
    static Queue<Integer> word;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int idx_A = 1, idx_B = 1;
        word = new ArrayDeque<>();

        while (idx_A <= n && idx_B <= m) {
            int result = 0;
            for (int i = idx_A; i <= n; i++) {
                for (int j = idx_B; j <= m; j++) {
                    if(a[i] == b[j])
                        result = Math.max(result, a[i]);
                }
            }

            if (result != 0) {
                word.offer(result);
                while(a[idx_A] != result)
                    idx_A++;

                while(b[idx_B] != result)
                    idx_B++;

                idx_A++;
                idx_B++;
            } else break;
        }

        sb.append(word.size()).append('\n');
        while (!word.isEmpty()) {
            sb.append(word.poll()).append(" ");
        }
        System.out.println(sb);

    }
}
