import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int t, s;
    static String tgt, src;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()), cnt = 0;
        t = (tgt = br.readLine()).length();

        while (n-- > 0) {
            s = (src = br.readLine()).length();
            for (int i = 1; i <= s / (t - 1); i++) {
                if (compare(i)) {
                    cnt++;
                    break;
                }
            }
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
    }

    private static boolean compare(int gap) {
        for (int i = 0; i < s - t + 1; i++) {
            int idx = i, j;
            for (j = 0; j < t && idx < s; j++, idx += gap) if (tgt.charAt(j) != src.charAt(idx)) break;

            if (j == t) return true;
        }

        return false;
    }

}