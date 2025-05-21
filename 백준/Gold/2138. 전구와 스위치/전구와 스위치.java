import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static boolean[] from, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        from = new boolean[n];
        target = new boolean[n];

        String str1 = br.readLine();
        String str2 = br.readLine();

        for (int i = 0; i < n; i++) {
            from[i] = str1.charAt(i) == '1';
            target[i] = str2.charAt(i) == '1';
        }

        int res1 = trySwitch(Arrays.copyOf(from, n), false);
        int res2 = trySwitch(Arrays.copyOf(from, n), true);

        int ans = Integer.MAX_VALUE;
        if (res1 != -1) ans = Math.min(ans, res1);
        if (res2 != -1) ans = Math.min(ans, res2);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int trySwitch(boolean[] bulbs, boolean firstSwitch) {
        int count = 0;
        if (firstSwitch) {
            toggle(bulbs, 0);
            count++;
        }

        for (int i = 1; i < n; i++) {
            if (bulbs[i - 1] != target[i - 1]) {
                toggle(bulbs, i);
                count++;
            }
        }

        if (Arrays.equals(bulbs, target)) return count;
        return -1;
    }

    static void toggle(boolean[] arr, int idx) {
        for (int i = idx - 1; i <= idx + 1; i++) {
            if (i >= 0 && i < n) arr[i] = !arr[i];
        }
    }
}