import java.io.*;
import java.util.*;

public class Main {
    static int[][] prefixSum;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int len = str.length();
        prefixSum = new int[len + 1][26];

        for (int i = 1; i <= len; i++) {
            int ch = str.charAt(i - 1) - 'a';
            for (int j = 0; j < 26; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j];
            }
            prefixSum[i][ch]++;
        }

        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char target = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int t = target - 'a';
            int result = prefixSum[end + 1][t] - prefixSum[start][t];
            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }
}