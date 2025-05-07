import java.io.*;
import java.util.*;

public class Main {
    static int n, d, k, c;
    static int[] arr;
    static int[] count;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //초밥 접시수
        n = Integer.parseInt(st.nextToken());
        //초밥 가짓수
        d = Integer.parseInt(st.nextToken());
        //연속해서 먹는 접시의 수
        k = Integer.parseInt(st.nextToken());
        //쿠폰 번호
        c = Integer.parseInt(st.nextToken());

        arr = new int[n * 2];
        count = new int[d + 1];


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            arr[i + n] = arr[i];
        }

        int kind = 0;

        for (int i = 0; i < k; i++) {
            if (count[arr[i]] == 0) {
                kind++;
            }
            count[arr[i]]++;
        }

        max = (count[c] == 0) ? kind + 1 : kind;

        for (int i = 1; i < n; i++) {
            int remove = arr[i - 1];

            count[remove]--;
            if (count[remove] == 0) {
                kind--;
            }

            int add = arr[i + k - 1];
            if (count[add] == 0) {
                kind++;
            }
            count[add]++;

            int total = count[c] == 0 ? kind + 1 : kind;
            max = Math.max(max, total);
        }
        System.out.println(max);
    }
}
