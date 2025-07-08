import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int left = 0;
        int right = 0;

        Arrays.sort(arr);

        while (right < n) {
            if(arr[right] - arr[left] < m){
                right++;
                continue;
            }

            if (arr[right] - arr[left] == m) {
                min = m;
                break;
            }

            min = Math.min(min, arr[right] - arr[left]);
            left++;
        }
        System.out.println(min);
    }
}
