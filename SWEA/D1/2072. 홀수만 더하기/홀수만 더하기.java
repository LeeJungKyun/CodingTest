import java.util.Scanner;
import java.util.StringTokenizer;

class Solution {
    static int T;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int sum = 0;
            int n;
            for (int j = 0; j < 10; j++) {
                 n = sc.nextInt();
                if (n % 2 != 0) {
                    sum += n;
                }
            }
            System.out.println("#" + (i + 1) +" " +  sum);
        }
    }
}
