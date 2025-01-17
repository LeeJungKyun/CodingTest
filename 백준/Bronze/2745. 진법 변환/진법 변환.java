import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String N;
    static int b;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = st.nextToken();
        b = Integer.parseInt(st.nextToken());

        int tmp = 1;

        for (int i = N.length() - 1; i >= 0; i--) {
            char c = N.charAt(i);

            if ('0' <= c && c <= '9') {
                result += (c - '0') * tmp;
            } else {
                result += (c-'A' + 10) * tmp;
            }

            tmp *= b;
        }

        System.out.println(result);
    }
}
