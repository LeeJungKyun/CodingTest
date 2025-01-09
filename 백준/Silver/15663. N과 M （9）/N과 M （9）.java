import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] result;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        result = new int[m];
        visited = new boolean[n];


        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0);
    }

    public static void dfs(int depth) {
        //수열 출력
        if (depth == m) {
            for (int val : result) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        } else {
            int before = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    continue;
                }

                if (before != arr[i]) {
                    visited[i] = true;
                    result[depth] = arr[i];
                    before = arr[i];
                    dfs(depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
