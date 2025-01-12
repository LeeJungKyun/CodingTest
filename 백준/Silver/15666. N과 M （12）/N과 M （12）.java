import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        result = new int[m];
        //배열 입력
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0, 0);
    }

    public static void dfs(int start, int depth) {
        if (depth == m) {
            for (int val : result) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        }

        int before = 0;

        for (int i = start; i < n; i++) {
            if (arr[i] != before) {
                result[depth] = arr[i];
                before = arr[i];
                dfs(i, depth + 1);
            }
        }

    }
}
