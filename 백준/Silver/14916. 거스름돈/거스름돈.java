import java.util.Scanner;

public class Main {
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        //5원짜리랑 2원짜리만 있음
        int count = 0;
        int temp = 0;

        while (true) {
            if (n % 5 == 0) {
                count += n / 5;
                System.out.println(count);
                break;
            } else {
                n -= 2;
                count++;
            }
            if (n < 0) {
                System.out.println(-1);
                break;
            }
        }
    }
}
