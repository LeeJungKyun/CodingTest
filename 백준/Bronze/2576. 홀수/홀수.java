import java.util.Scanner;

public class Main {
    static int min = Integer.MAX_VALUE;
    static int n;
    static int sum = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
            n = sc.nextInt();
            if (n % 2 != 0) {
                sum += n;
                if (n < min) {
                    min = n;
                }
            }
        }
        if (sum != 0) {
            System.out.println(sum);
            System.out.println(min);
        } else System.out.println("-1");
    }
}
