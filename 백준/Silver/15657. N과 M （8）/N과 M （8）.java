import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] res;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        res = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0, 0);

        System.out.println(sb);
    }

    public static void backTrack(int idx, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(res[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = idx; i < n; i++) {
            res[depth] = arr[i];
            backTrack(i, depth + 1);
        }
    }
}
