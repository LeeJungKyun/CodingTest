import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
    static int T;
    static int N;
    static int[] arr;
    static long max_Price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            max_Price = Long.MIN_VALUE;

            int num = 0;
            long cost = 0L;
            long result = 0L;

            for (int i = N - 1; i >= 0; i--) {
                if (arr[i] > max_Price) {
                    result += (max_Price * num - cost);
                    max_Price = arr[i];

                    cost = 0;
                    num = 0;
                } else {
                    cost += arr[i];
                    num++;
                }
            }

            result += (max_Price * num - cost);

            System.out.println("#" + (t + 1) + " " + result);
        }
    }
}
