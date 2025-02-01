import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int[] prefix;
    static int sum;
    static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[n];
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            sum += arr[i];
            prefix[i + 1] = sum;
        }

        for (int i = 0; i < n; i++) {
            result += ((long) arr[i] * (prefix[n] - prefix[i + 1]));
        }
        System.out.println(result);
    }
}
