import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static BigDecimal a;
    static int b;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        a = sc.nextBigDecimal();
        b = sc.nextInt();

        BigDecimal result = a.pow(b);

        System.out.println(result.toPlainString());
    }
}
