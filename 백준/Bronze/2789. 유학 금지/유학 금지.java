import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] bannedWord = "CAMBRIDGE".toCharArray();

        String str = sc.next();

        for (int i = 0; i < bannedWord.length; i++) {
            if (str.indexOf(bannedWord[i]) > -1) {
                str = str.replace(String.valueOf(bannedWord[i]), "");
            }
        }
        System.out.println(str);
    }
}
