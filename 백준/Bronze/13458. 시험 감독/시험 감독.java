import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, b, c;
    static int[] a;
    static long cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cnt += n;

        for (int i = 0; i < n; i++) {
            a[i] = a[i] - b;
            if(a[i] <= 0) continue;
            cnt += a[i] / c;
            if (a[i] % c != 0) {
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}
