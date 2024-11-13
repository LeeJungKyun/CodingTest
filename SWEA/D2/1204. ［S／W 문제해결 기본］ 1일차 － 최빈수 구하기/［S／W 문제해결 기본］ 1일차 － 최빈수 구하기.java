import java.io.IOException;
import java.util.Scanner;

public class Solution {
    static int T;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            arr = new int[101];
            sc.next();
            for (int i = 0; i < 1000; i++) {
                arr[sc.nextInt()]++;
            }
            int max = arr[0];
            int max_index = 0;
            for (int i = 1; i < 101; i++) {
//                System.out.println("arr[" + i + "] = " + arr[i]);
                if (max <= arr[i]) {
                    max = arr[i];
                    max_index = i;
//                    System.out.println("max = " + max);
                }
            }
            System.out.println("#" + (t+1) + " " + max_index);
        }
    }
}
