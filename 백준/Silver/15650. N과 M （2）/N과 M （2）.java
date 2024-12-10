import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] arr;
    static boolean[] isUsed;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[m];

        backTrack(1,0);
    }

    private static void backTrack(int start, int depth) {
        //수열이 다 차면 출력
        if (depth == m) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= n; i++) {
            arr[depth] = i;
            backTrack(i + 1, depth + 1);
        }
    }

}