import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            query(l - 1, r - 1, x);
        }

        StringBuilder sb = new StringBuilder();

        for(long num : arr)
            sb.append(num + " ");

        System.out.println(sb);
    }

    public static void query(int l, int r, int x) {
        for (int i = l; i <= r; i++) {
            arr[i] += x;
        }
        Arrays.sort(arr);
    }
}
