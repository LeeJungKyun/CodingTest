import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[] arr, result;
    static boolean[] isUsed;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        result = new int[n];
        isUsed = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        backTrack(0);
    }

    public static void backTrack(int depth) {
        if (depth == n) {
            for (int val : result) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isUsed[i]) {
                continue;
            }
            
            isUsed[i] = true;
            result[depth] = i + 1;
            backTrack(depth + 1);
            isUsed[i] = false;
        }

    }
}
