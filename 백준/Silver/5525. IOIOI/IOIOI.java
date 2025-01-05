import java.util.Scanner;

public class Main {
    static int n, m;
    static char[] str;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        str = new char[m];
        str = sc.next().toCharArray();

        int count = 0;
        int patternCount = 0;

        for (int i = 1; i < m - 1; i++) {
            if (str[i - 1] == 'I' && str[i] == 'O' && str[i + 1] == 'I') {
                patternCount++;
                if (patternCount == n) {
                    count++;
                    patternCount--;
                }
                i++;
            } else {
                patternCount = 0;
            }
        }
        System.out.println(count);
    }
}
