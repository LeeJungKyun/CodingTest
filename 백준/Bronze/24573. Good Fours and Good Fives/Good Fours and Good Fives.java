import java.util.Scanner;

public class Main {
    public static int solveCombination(int n) {
        if (n <= 0) {
            return 0;
        }

        int count = 0;
        
        int max_y = n / 5;

        for (int y = 0; y <= max_y; y++) {
            int remaining = n - (5 * y);
            
            if (remaining >= 0 && remaining % 4 == 0) {
                count++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            
            int result = solveCombination(number);
            
            System.out.println(result);
        }

        scanner.close();
    }
}