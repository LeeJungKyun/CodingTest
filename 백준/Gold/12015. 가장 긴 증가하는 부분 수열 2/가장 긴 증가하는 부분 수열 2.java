import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr, lis;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        lis = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = arr[0];
        result = 1;

        for (int i = 1; i < n; i++) {
            int cur = arr[i];

            if (lis[result - 1] < cur) {
                result++;
                lis[result - 1] = cur;
            }
            else{
                int left = 0;
                int right = result;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if(lis[mid] < cur)
                        left = mid + 1;
                    else right = mid;
                }

                lis[left] = cur;
            }
        }

        System.out.println(result);
    }
}
