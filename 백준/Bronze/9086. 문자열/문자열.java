import java.util.Scanner;

public class Main {
    static int testCase;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        testCase = sc.nextInt();

        for (int t = 0; t < testCase; t++) {
            String str = sc.next();
            System.out.print(str.charAt(0));
            System.out.print(str.charAt(str.length() - 1));
            System.out.println();
        }
    }
}
