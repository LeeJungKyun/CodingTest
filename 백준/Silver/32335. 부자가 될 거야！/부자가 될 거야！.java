import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        final int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        char[] S = br.readLine().toCharArray();

        for (int i = 0; i < N - 1; ++i) {
            if (S[i] > '0') {
                final int num = S[i] - '0';
                if (num + M >= 10) {
                    S[i] = '0';
                    M -= (10 - num);
                }
            }
        }
        if (M > 0) {
            final int num = S[N - 1] - '0';
            final int r = M % 10;
            S[N - 1] = (char) ((num + M) % 10 + '0');
        }

        System.out.println(S);
    }
}