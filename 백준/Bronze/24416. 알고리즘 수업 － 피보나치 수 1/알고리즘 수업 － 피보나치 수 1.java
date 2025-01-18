import java.util.Scanner;

public class Main {
    static int n;
    static int cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        fib(n);

        System.out.println(cnt + " " + (n - 2));
    }

    public static int fib(int n) {
        if (n == 1 || n == 2) {
            cnt++;
            return 1;
        } else {
            return (fib(n - 1) + fib(n - 2));
        }
    }
}
