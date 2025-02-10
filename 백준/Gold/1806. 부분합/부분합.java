import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, s;
    static int[] arr;
    static int[] prefixSum;
    static int min = Integer.MAX_VALUE;
    static int start, end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        prefixSum = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        start = 0;
        end = 1;

        while (end <= n && start <= n) {
            int sum = prefixSum[end] - prefixSum[start];
            if (sum >= s && min > end - start) {
                min = end - start;
            }
            if (sum >= s && start < end) {
                start++;
            }
            if (sum < s || start == end) {
                end++;
            }
        }
        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        }
        else System.out.println(min);
    }
}
