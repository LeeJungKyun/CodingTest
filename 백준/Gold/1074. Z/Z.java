import java.util.Scanner;

public class Main {
    static int count = 0;
    static int r, c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        int size = (int) Math.pow(2, N); // 행렬 크기
        solve(0, 0, size);
    }

    public static void solve(int x, int y, int size) {
        // Base case: 1x1 크기 도달
        if (size == 1) {
            System.out.println(count);
            return;
        }

        int half = size / 2;

        // 1사분면 (좌상)
        if (r < x + half && c < y + half) {
            solve(x, y, half);
        }
        // 2사분면 (우상)
        else if (r < x + half && c >= y + half) {
            count += half * half; // 이전 1사분면 스킵
            solve(x, y + half, half);
        }
        // 3사분면 (좌하)
        else if (r >= x + half && c < y + half) {
            count += 2 * half * half; // 이전 2사분면까지 스킵
            solve(x + half, y, half);
        }
        // 4사분면 (우하)
        else {
            count += 3 * half * half; // 이전 3사분면까지 스킵
            solve(x + half, y + half, half);
        }
    }
}