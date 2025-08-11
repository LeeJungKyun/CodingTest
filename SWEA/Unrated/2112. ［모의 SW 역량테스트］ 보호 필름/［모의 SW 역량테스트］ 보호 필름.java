import java.io.*;
import java.util.*;

public class Solution {
    static int testCase;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int d, w, k;
    static int[][] film;
    static int minChange;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            film = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // k == 1이거나 약품 없이도 통과하면 0 출력
            if (k == 1 || isPossible(film)) {
                System.out.println("#" + tc + " 0");
                continue;
            }

            minChange = Integer.MAX_VALUE;
            dfs(0, 0);
            System.out.println("#" + tc + " " + minChange);
        }
    }

    // DFS 백트래킹
    static void dfs(int row, int changeCount) {
        if (changeCount >= minChange) return; // 가지치기
        if (row == d) {
            if (isPossible(film)) {
                minChange = changeCount;
            }
            return;
        }

        int[] original = film[row].clone(); // 현재 행 백업

        // 1. 변경 안 하고 넘기기
        dfs(row + 1, changeCount);

        // 2. A 약품 (0으로 변경)
        Arrays.fill(film[row], 0);
        dfs(row + 1, changeCount + 1);

        // 3. B 약품 (1로 변경)
        Arrays.fill(film[row], 1);
        dfs(row + 1, changeCount + 1);

        // 원상 복구
        film[row] = original;
    }

    // 성능 검사
    static boolean isPossible(int[][] film) {
        for (int col = 0; col < w; col++) {
            int cnt = 1;
            boolean passed = false;
            for (int row = 1; row < d; row++) {
                if (film[row][col] == film[row - 1][col]) {
                    cnt++;
                    if (cnt >= k) {
                        passed = true;
                        break;
                    }
                } else {
                    cnt = 1;
                }
            }
            if (!passed && cnt < k) return false;
        }
        return true;
    }
}
