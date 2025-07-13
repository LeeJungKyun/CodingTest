import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] alpha, arr;
    static String a, b;
    public static void main(String[] args) throws IOException {
        alpha = new int[] {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length() * 2; i++) {
            //A 차례
            if (i % 2 == 0) {
                sb.append(a.charAt(i / 2));
            } else sb.append(b.charAt(i / 2));
        }

        arr = new int[sb.length()];

        for (int i = 0; i < sb.length(); i++) {
            arr[i] = alpha[sb.charAt(i) - 65];
        }

        int size = arr.length;

        while (size > 2) {
            for (int i = 0; i < size - 1; i++) {
                arr[i] = ((arr[i] + arr[i + 1]) % 10);
            }
            arr[--size] = 0;
        }

        System.out.println(String.valueOf(arr[0]) + String.valueOf(arr[1]));

    }
}
