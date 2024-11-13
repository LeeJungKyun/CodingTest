import java.util.Scanner;

public class Solution {
    static int T;
    static int A,B,N;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            //x 저장값
            A = sc.nextInt();
            //y 저장값
            B = sc.nextInt();
            //N 목표값
            N = sc.nextInt();

            int cnt = 0;

            while (Math.max(A,B) <= N) {
                if (A > B) {
                    B += A;
                }
                else A += B;
                cnt++;
            }
            System.out.println(cnt );
        }
    }
}
