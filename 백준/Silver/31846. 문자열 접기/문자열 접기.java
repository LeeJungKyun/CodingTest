import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int l, r;
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        str = br.readLine();

        int q = Integer.parseInt(br.readLine());

        for (int t = 0; t < q; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            l = Integer.parseInt(st.nextToken()) - 1;
            r = Integer.parseInt(st.nextToken()) - 1;

            int max = 0;
            for (int i = l; i < r; i++) {
                max = Math.max(max, getMax(i));
            }
            System.out.println(max);
        }
    }

    private static int getMax(int num) {
        int cnt = 0;
        int j = num + 1;

        while (num >= l && j <= r) {
            if (str.charAt(num--) == str.charAt(j++)) {
                cnt++;
            }
        }
        return cnt;
    }
}
