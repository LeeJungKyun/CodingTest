import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int twoComplement = ~N + 1;

        int diffBits = N ^ twoComplement;

        int count = Integer.bitCount(diffBits);

        System.out.println(count);
    }
}