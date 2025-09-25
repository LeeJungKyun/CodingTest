import java.util.Scanner;

public class Main {
    static int t, n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        for (int testCase = 0; testCase < t; testCase++) {
            n = sc.nextInt();
            System.out.println(findRight(n));
        }
    }

    private static int findRight(int n) {
    	
    	if(n < 3) {
    		return n;
    	}
    	
        int num = 1;

        for (int i = 2; i <= n; i++) {
            num *= i;
            while (num % 10 == 0) {
                num /= 10;
            }
            num %= 100000;
        }
        return num % 10;
    }
}
