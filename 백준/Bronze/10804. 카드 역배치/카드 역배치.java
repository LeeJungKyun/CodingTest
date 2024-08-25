import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr = new int[21];
    static int start, end;

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 20; i++) {
            arr[i] = i;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            swapCard(start, end);
        }

        for (int i = 1; i <= 20; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void swapCard(int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}