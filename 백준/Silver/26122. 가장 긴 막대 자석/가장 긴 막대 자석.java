import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String s = sc.next();
        
        int l = 1, r = 0, result = 0;
        char st = s.charAt(0);
        boolean flag = false;

        for (int i = 1; i < k; i++) {
            if (s.charAt(i) == st) {
                if (!flag) {
                    l++;
                } else {
                    r++;
                }
            } else {
                st = s.charAt(i);
                flag = !flag;
                if (r == 0) {
                    r = 1;
                } else {
                    result = Math.max(result, Math.min(l, r) * 2);
                    l = r;
                    r = 1;
                    flag = !flag;
                }
            }
        }
        result = Math.max(result, Math.min(l, r) * 2);
        System.out.println(result);
    }
}