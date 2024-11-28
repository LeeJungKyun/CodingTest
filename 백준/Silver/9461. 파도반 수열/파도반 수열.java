import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    static int T, N;
    static Long[] arr;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            arr = new Long[101];
            arr[0] = 0L;
            arr[1] = 1L;
            arr[2] = 1L;
            arr[3] = 1L;
            N = sc.nextInt();
            System.out.println(Triangle(N));
        }
    }

    private static long Triangle(int n) {
        if (arr[n] == null) {
            arr[n] = Triangle(n - 2) + Triangle(n - 3);
        }
        return arr[n];
    }
}
