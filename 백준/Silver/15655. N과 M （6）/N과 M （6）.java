import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] res;
    static boolean[] isUsed;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        res = new int[m];
        isUsed = new boolean[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0, 0);

        System.out.println(sb);
    }

    public static void backTrack(int depth, int idx) {
        if (depth == m) {
            for (int num : res) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = idx; i < n; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                res[depth] = arr[i];
                backTrack(depth + 1, i);
                isUsed[i] = false;
            }
        }
    }
}
