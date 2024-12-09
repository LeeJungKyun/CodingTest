import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        for (int testCase = 0; testCase < n; testCase++) {
            String str = sc.nextLine();
            int result = 0;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch != ' ') {
                    result += ch - 'A' + 1;
                }
            }

            if (result == 100) {
                System.out.println("PERFECT LIFE");
            } else {
                System.out.println(result);
            }
        }
    }
}