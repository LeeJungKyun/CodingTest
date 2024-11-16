import java.util.Scanner;

public class Solution {
    static int T;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            String str = sc.next();
            for (int i = 1; i <= str.length(); i++) {
                String a = str.substring(0,i);
                String b = str.substring(i, i + i);
                if (a.equals(b)) {
                    System.out.println("#" + (t + 1) + " " + b.length());
                    break;
                }
            }
        }
    }
}
