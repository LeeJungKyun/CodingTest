import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 감소하는 수
 * 0 <= N <= 1_000_000
 * 1, 2, 3, 4, 5, 6, 7, 8, 9
 * 10, 20, 21, 30, 31, 32
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static ArrayList<Long> decreasingNumbers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        //1. n 입력받기
        n = Integer.parseInt(br.readLine());

        //조기 종료 조건
        if (n <= 9) {
            System.out.println(n);
            return;
        }

        for (int i = 0; i < 10; i++) {
            decreasingNumbers.add((long) i);
            findNth((long) i, i);
        }

        Collections.sort(decreasingNumbers);

        if (n >= decreasingNumbers.size()) {
            System.out.println(-1);
        } else System.out.println(decreasingNumbers.get(n));
    }

    public static void findNth(long currentNum, int lastDigit) {
        for (int i = 0; i < lastDigit; i++) {
            long nextNum = currentNum * 10 + i;
            decreasingNumbers.add(nextNum);
            findNth(nextNum, i);
        }
    }
}
