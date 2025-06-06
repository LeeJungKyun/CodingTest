import java.io.*;
import java.util.*;

public class Main {
    static int[] alpha;
    static StringBuilder sb = new StringBuilder();
    static String answer = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        alpha = new int[26];
        for (int i = 0; i < str.length(); i++) {
            alpha[str.charAt(i) - 'A']++;
        }

        int oddCount = 0;
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] % 2 != 0)
                oddCount++;
        }

        if (oddCount > 1)
            answer += ("I'm Sorry Hansoo");
        else {
            for (int i = 0; i < alpha.length; i++) {
                for (int j = 0; j < alpha[i] / 2; j++) {
                    sb.append((char) (i + 65));
                }
            }
            answer += sb.toString();
            String end = sb.reverse().toString();

            sb = new StringBuilder();
            for (int i = 0; i < alpha.length; i++) {
                if (alpha[i] % 2 == 1) {
                    sb.append((char) (i + 65));
                }
            }
            answer += sb.toString() + end;
        }
        System.out.println(answer);
    }
}
