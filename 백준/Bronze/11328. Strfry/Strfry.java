import java.util.Scanner;

public class Main {
    static int n;
    static int[] arr1;
    static int[] arr2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String str1 = sc.next();
            String str2 = sc.next();

            arr1 = new int[26];
            arr2 = new int[26];

            for (int j = 0; j < str1.length(); j++) {
                char c = str1.charAt(j);
                arr1[c - 97]++;
            }

            for (int j = 0; j < str2.length(); j++) {
                char c = str2.charAt(j);
                arr2[c - 97]++;
            }

            boolean possible = true;
            int p;

            for (p = 0; p < 26; p++) {
                if (arr1[p] != arr2[p]) {
                    System.out.println("Impossible");
                    possible = false;
                    break;
                }
            }
            if (possible) {
                System.out.println("Possible");
            }
        }
    }

}
