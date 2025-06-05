import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static boolean[] check;
    static int result;
    static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        check = new boolean[26];
        words = new String[n];

        if(k < 5){
            System.out.println(0);
            return;
        } else if (k == 26){
            System.out.println(n);
            return;
        }

        check['a' - 'a'] = true;
        check['n' - 'a'] = true;
        check['t' - 'a'] = true;
        check['i' - 'a'] = true;
        check['c' - 'a'] = true;

        for (int i = 0; i < n; i++) {
            String str = br.readLine().replaceAll("anta|tica", "");
            words[i] = str;
        }

        find(0, 0);

        System.out.println(result);
    }

    static void find(int alphabet, int count) {
        if (count == k - 5) {
            int temp = 0;
            for (int i = 0; i < n; i++) {
                boolean flag = true;

                for (int j = 0; j < words[i].length(); j++) {
                    if(!check[words[i].charAt(j) - 'a']){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    temp++;
                }
            }

            result = Math.max(temp, result);
            return;
        }

        for (int i = alphabet; i < 26; i++) {
            if(!check[i]){
                check[i] = true;
                find(i, count + 1);
                check[i] = false;
            }
        }
    }
}
