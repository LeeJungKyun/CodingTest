import java.io.*;
import java.util.*;

public class Main {
    static long n, result, bit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        bit = 1;

        while (n > 0) {
            if ((n & 1) == 1)
                result += bit;
            bit *= 3;
            n >>= 1;
        }

        System.out.println(result);
    }
}
