import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[] arr;
    static int left, right;
    static long min = Long.MAX_VALUE;
    static int[] result = new int[3];
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        //가장 작은 용액을 기준으로 left, right 선정
        for (int start = 0; start < n - 2; start++) {
            left = start + 1;
            right = n - 1;
            while (left < right) {
                long sum = arr[start] + arr[left] + arr[right];
                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    result[0] = start;
                    result[1] = left;
                    result[2] = right;
                }
                if(sum == 0)
                    break;
                else if (sum > 0) {
                    right --;
                } else left++;
            }
        }

        sb.append(arr[result[0]])
                .append(" ")
                .append(arr[result[1]])
                .append(" ")
                .append(arr[result[2]]);

        System.out.println(sb);
    }
}
