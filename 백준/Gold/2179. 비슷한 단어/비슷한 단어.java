import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<String> list;
    static int max = Integer.MIN_VALUE;
    static int idx1, idx2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(br.readLine());
        }

        for (int i = 0; i < n - 1; i++) {
            String str1 = list.get(i);

            for (int j = i + 1; j < n; j++) {
                int cnt = 0;

                String str2 = list.get(j);
                cnt = check(str1, str2);
                if (cnt > max) {
                    max = cnt;
                    idx1 = i;
                    idx2 = j;
                }
            }
        }

        System.out.println(list.get(idx1));
        System.out.println(list.get(idx2));
    }

    public static int check(String str1, String str2) {
        int cnt = 0;
        int size = Math.min(str1.length(), str2.length());

        for (int i = 0; i < size; i++) {
            if(str1.charAt(i) == str2.charAt(i))
                cnt++;
            else break;
        }
        return cnt;
    }
}
