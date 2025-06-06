import java.io.*;
import java.util.*;

public class Main {
    static char[] alphabet, result;
    static int l, c;
    static final String vowels = "aeiou";
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        alphabet = new char[c];
        result = new char[l];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabet);
        dfs(0, 0);
        System.out.println(sb);

    }

    public static void dfs(int depth, int idx) {
        if (depth == l) {
            if (isValid(result)) {
                sb.append(new String(result)).append('\n');
            }
            return;
        }
        for (int i = idx; i < c; i++) {
            result[depth] = alphabet[i];
            dfs(depth + 1, i + 1);
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
