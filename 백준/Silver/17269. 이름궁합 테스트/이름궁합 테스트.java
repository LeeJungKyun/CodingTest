import java.io.*;
import java.util.*;

public class Main {
    static int[] alphabet = new int[] {
        3, 2, 1, 2, 4, 3, 1, 3, 1, 1, 3, 1, 3, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        String name1 = st.nextToken();
        String name2 = st.nextToken();

        StringBuilder sb = new StringBuilder();
        int len = Math.max(n, m);
        for (int i = 0; i < len; i++) {
            if(i < n) sb.append(name1.charAt(i));
            if(i < m) sb.append(name2.charAt(i));
        }

        arr = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            arr[i] = alphabet[sb.charAt(i) - 'A'];
        }

        while (arr.length > 2) {
            int[] next = new int[arr.length - 1];
            for (int i = 0; i < arr.length - 1; i++) {
                next[i] = (arr[i] + arr[i +  1]) % 10;
            }
            arr = next;
        }

        int a = arr[0];
        int b = arr[1];

        if (a == 0) {
            System.out.println(b + "%");
        } else {
            System.out.println(a + "" + b + "%");
        }
    }
}
