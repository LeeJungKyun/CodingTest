import java.io.*;
import java.util.*;


public class Main {
    static int t;
    static String str;
    static int k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            str = br.readLine();
            int k = Integer.parseInt(br.readLine());

            if (k == 1) {
                sb.append(1).append(" ").append(1).append('\n');
                continue;
            }
            //알파벳 저장 변수
            arr = new int[26];

            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < str.length(); i++) {
                if(arr[str.charAt(i) - 'a'] < k)
                    continue;

                int count = 1;
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(i) == str.charAt(j)) {
                        count++;
                    }
                    if (count == k) {
                        min = Math.min(min, j - i + 1);
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }

            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                sb.append("-1").append('\n');
            } else sb.append(min).append(" ").append(max).append('\n');
        }
        System.out.println(sb);
    }
}
