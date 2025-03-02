import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] isUsed;
    static int[] result;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        result = new int[n];
        isUsed = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backTrack(0);
        System.out.println(max);
    }

    public static void backTrack(int depth) {
        if (depth == n) {
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += Math.abs(result[i] - result[i + 1]);
            }
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                result[depth] = arr[i];
                backTrack(depth + 1);
                isUsed[i] = false;
            }
        }
    }
}
