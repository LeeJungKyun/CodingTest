import java.io.*;

public class Main {
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        toOne(n, 0);
        System.out.println(ans);
    }

    static void toOne(int num, int cnt) {
        if (num == 1) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (cnt >= ans) return;

        if (num % 3 == 0) {
            toOne(num / 3, cnt + 1);
        }
        if (num % 2 == 0) {
            toOne(num / 2, cnt + 1);
        }
        toOne(num - 1, cnt + 1);
    }
}