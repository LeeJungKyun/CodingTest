import java.io.*;
import java.util.*;


/**
 * @author 이정균
 * mCn 구하는 문제 DP로 ㄱㄱ
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 */
import java.io.*;
import java.util.*;

public class Main {

    static int[][] dp = new int[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            sb.append(Combi(n, r)).append('\n');
        }

        System.out.println(sb);
    }

    static int Combi(int n, int r) {

        if (dp[n][r] > 0) {
            return dp[n][r];
        }

        if (n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = Combi(n - 1, r - 1) + Combi(n - 1, r);
    }
}