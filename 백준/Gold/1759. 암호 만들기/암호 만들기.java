import java.io.*;
import java.util.*;

public class Main {
    static char[] alphabet, result;
    static int length, characterCount;
    static final String vowels = "aeiou";
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        characterCount = Integer.parseInt(st.nextToken());

        alphabet = new char[characterCount];
        result = new char[length];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < characterCount; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabet);
        makePassword(0, 0);
        System.out.println(sb);

    }

    public static void makePassword(int depth, int idx) {
        if (depth == length) {
            if (isValid(result)) {
                sb.append(new String(result)).append('\n');
            }
            return;
        }
        for (int i = idx; i < characterCount; i++) {
            result[depth] = alphabet[i];
            makePassword(depth + 1, i + 1);
        }
    }

    public static boolean isValid(char[] arr) {
        int vowelCount = 0, consonantCount = 0;
        for (char ch : arr) {
            if(vowels.indexOf(ch) != -1)
                vowelCount++;
            else consonantCount++;
        }
        return vowelCount >= 1 && consonantCount >= 2;
    }
}
