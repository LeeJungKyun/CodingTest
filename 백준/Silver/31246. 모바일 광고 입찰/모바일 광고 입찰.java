import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        a = new int[n];
        b = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 1000000000; // 가능한 큰 값으로 설정
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canWinBids(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canWinBids(int x) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] + x >= b[i]) {
                count++;
            }
            if (count >= k) {
                return true;
            }
        }
        return false;
    }
}