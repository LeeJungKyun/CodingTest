import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5 * n; j++) {
                sb.append('@');
            }
            sb.append('\n');
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append('@');
            }
            sb.append('\n');
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5 * n; j++) {
                sb.append('@');
            }
            sb.append('\n');
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append('@');
            }
            sb.append('\n');
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5 * n; j++) {
                sb.append('@');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
        
    }

}
