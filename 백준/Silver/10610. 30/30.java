import java.io.*;
import java.util.*;

public class Main {
    static String n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine();

        char[] arr = n.toCharArray();
        Arrays.sort(arr);

        int sum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i] - '0';
            sum += num;
            sb.append(num);
        }

        if (sum % 3 != 0 || arr[0] != '0') {
            System.out.println(-1);
            return;
        }
        System.out.println(sb);
    }
}
