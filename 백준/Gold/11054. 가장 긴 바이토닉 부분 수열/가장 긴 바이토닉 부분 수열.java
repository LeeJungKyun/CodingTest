import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int[] dpLeft;
    static int[] dpRight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dpLeft = new int[n + 1];
        dpRight = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            dpLeft[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i] && dpLeft[i] < dpLeft[j] + 1) {
                    dpLeft[i] = dpLeft[j] + 1;
                }
            }
        }

        for (int i = n; i >= 1; i--) {
            dpRight[i] = 1;
            for (int j = n; j > i; j--) {
                if (arr[j] < arr[i] && dpRight[i] < dpRight[j] + 1) {
                    dpRight[i] = dpRight[j] + 1;
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= n; i++) {
            int max = Math.max((dpLeft[i] + dpRight[i] - 1), Math.max(dpLeft[i], dpRight[i]));
            result = Math.max(max, result);
        }

        System.out.println(result);
    }
}
