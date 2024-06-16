import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 9663 N-Queen
 * 사용 알고리즘
 * 1. 백트래킹
 */
public class Main {
    static int n;
    static int[][] board;
    static int cnt;
    static boolean[] visited1;
    static boolean[] visited2;
    static boolean[] visited3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        //체스판 선언
        board = new int[n][n];
        //열체크
        visited1 = new boolean[n];
        //오른쪽 아래 대각선( \ ) 배열 크기는 대각선의 개수
        visited2 = new boolean[2 * n - 1];
        //오른쪽 위 대각선( / )
        visited3 = new boolean[2 * n - 1];
        cnt = 0;
        queen(0);
        System.out.println(cnt);
    }

    public static void queen(int cur) {
        if (cur == n) {
            cnt++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited1[i] || visited2[i + cur] || visited3[cur - i + (n - 1)])
                continue;
            visited1[i] = visited2[i + cur] = visited3[cur - i + n - 1] = true;
            queen(cur+1);
            visited1[i] = visited2[i + cur] = visited3[cur - i + n - 1] = false;
        }
    }
}
