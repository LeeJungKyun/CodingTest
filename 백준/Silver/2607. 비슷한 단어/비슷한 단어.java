import java.io.*;
import java.util.*;

public class Main {
    static int n, result;
    static int[] baseAlphabet, wordAlphabet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        String base = br.readLine();
        baseAlphabet = new int[26];
        for (char ch : base.toCharArray()) {
            baseAlphabet[ch - 'A']++;
        }

        result = 0;

        for (int i = 1; i < n; i++) {
            String word = br.readLine();
            wordAlphabet = new int[26];
            for (char ch : word.toCharArray()) {
                wordAlphabet[ch - 'A']++;
            }

            int totalDiff = 0;
            for (int j = 0; j < 26; j++) {
                totalDiff += Math.abs(baseAlphabet[j] - wordAlphabet[j]);
            }

            int lenDiff = Math.abs(base.length() - word.length());

            if (lenDiff == 0 && (totalDiff == 0 || totalDiff == 2)) {
                result++;
            } else if (lenDiff == 1 && totalDiff == 1) {
                result++;
            }
        }

        System.out.println(result);
    }
}