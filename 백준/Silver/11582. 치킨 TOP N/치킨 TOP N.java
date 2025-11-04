import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i += (N / k)) {
            doSort(i, i + N / k);
        }

        System.out.println(sb);
    }

    private static void doSort(int start, int end) {
        int[] tmp = new int[end - start];
        for (int i = start; i < end; i++) {
            tmp[i - start] = arr[i];
        }
        Arrays.sort(tmp);
        for (int i = 0; i < end - start; i++) {
            sb.append(tmp[i]).append(" ");
        }
    }

}