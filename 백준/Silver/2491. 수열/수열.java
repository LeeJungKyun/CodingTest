import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int cnt_Increase, cnt_Decrease, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        cnt_Increase = 1;
        cnt_Decrease = 1;
        result = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] >= arr[i - 1]) {
                cnt_Increase++;
            } else {
                cnt_Increase = 1;
            }

            if (arr[i] <= arr[i - 1]) {
                cnt_Decrease++;
            } else {
                cnt_Decrease = 1;
            }
            result = Math.max(result, Math.max(cnt_Increase, cnt_Decrease));
        }
        System.out.println(result);

    }
}
