import java.io.*;
import java.util.*;

public class Main {
    static class Trade {
        int num;
        int cnt;

        public Trade(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static int n, k;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();
    }

    public static void bfs() {
        Queue<Trade> queue = new LinkedList<>();
        boolean[][] visited = new boolean[1000001][k + 1];

        //초기 주어진 값 삽입
        queue.add(new Trade(n, 0));
        visited[n][0] = true;

        while (!queue.isEmpty()) {
            Trade cur = queue.poll();

            if (cur.cnt == k) {
                result = Math.max(result, cur.num);
                continue;
            }

            int length = String.valueOf(cur.num).length();

            for (int i = 0; i < length - 1; i++) {
                for (int j = i + 1; j < length; j++) {
                    int next = swap(cur.num, i, j);

                    if (next != -1 && !visited[next][cur.cnt + 1]) {
                        queue.add(new Trade(next, cur.cnt + 1));
                        visited[next][cur.cnt + 1] = true;
                    }
                }
            }
        }
        System.out.println(result);
    }

    public static int swap(int n, int i, int j) {
        char[] numArr = String.valueOf(n).toCharArray();

        //불가능한 상황
        if (i == 0 && numArr[j] == '0') {
            return -1;
        }
        char temp;
        temp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = temp;

        return Integer.parseInt(new String(numArr));
    }
}
