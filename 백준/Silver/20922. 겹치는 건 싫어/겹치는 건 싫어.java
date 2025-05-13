import java.io.*;
import java.util.*;
public class Main {
    static int n, k;
    static int[] arr;
    static int max = Integer.MIN_VALUE;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        count = new int[100001];
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;

        while (right < arr.length) {
            while (right < arr.length && count[arr[right]] + 1 <= k) {
                count[arr[right]]++;
                right++;
            }
            int length = right - left;
            max = Math.max(max, length);
            count[arr[left]]--;
            left++;
        }
        System.out.println(max);
    }
}
