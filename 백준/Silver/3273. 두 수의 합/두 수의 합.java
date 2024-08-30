import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int n, x;
    static int[] arr;
    static int cnt = 0;
    static int start, end;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        x = sc.nextInt();

        Arrays.sort(arr);

        start = 0;
        end = n - 1;

        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == x) {
                cnt++;
                start++;
                end--;
            } else if (sum < x) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(cnt);
    }
}
