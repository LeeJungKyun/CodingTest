import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n / 2; i++) {
            int sum = 0;
            int index = i;
            while (true) {
                if (sum == n) {
                    result++;
                    break;
                }

                if (sum > n) {
                    break;
                }
                sum += index;
                index++;
            }
        }
        System.out.println(result + 1);
    }
}
