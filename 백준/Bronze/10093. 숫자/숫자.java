import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int A = Math.min(a, b);
        int B = Math.max(a, b);

        if (A == B) {
            System.out.print(0);
            return;
        }
        System.out.println(B - A - 1);
        for (int i = A + 1; i < B; i++) {
            System.out.print(i + " ");
        }
    }
}
