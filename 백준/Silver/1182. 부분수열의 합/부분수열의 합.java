import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int s;
    static int[] arr;
    static boolean[] visited;
    static int cnt;
    static boolean isEmpty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        function(0, 0, true);
        visited = new boolean[n];

        System.out.println(cnt);
    }

    public static void function(int k, int sum, boolean isEmpty) {
        if (k == n) {
            if (sum == s && !isEmpty) {
                cnt++;
            }
            return;
        }
        function(k + 1, sum, isEmpty);
        function(k + 1, sum + arr[k], false);
    }
}
