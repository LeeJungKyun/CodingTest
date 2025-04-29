import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] arr;
    static int[] res;
    static boolean[] visited;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        res = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backTrack(0);

        System.out.println(ans);
    }

    public static void backTrack(int depth){
        if (depth == n) {
            int stat = 500;
            for (int i = 0; i < n; i++) {
                stat += (res[i] - k);
                if (stat < 500) {
                    return;
                }
            }
            ans++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                res[depth] = arr[i];
                backTrack(depth + 1);
                visited[i] = false;
            }
        }
    }
}
