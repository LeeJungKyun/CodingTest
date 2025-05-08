import java.io.*;
import java.util.*;

public class Main {
    static int[][] data;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        data = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                data[i][j] = line.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            start(num - 1, dir);
        }

        int result = 0;

        for (int i = 0; i < 4; i++) {
            result += Math.pow(2, i) * data[i][0];
        }

        System.out.println(result);
    }

    public static void start(int idx, int dir) {
        leftChain(idx - 1, -dir);
        rightChain(idx + 1, -dir);
        rotate(idx, dir);
    }

    static void leftChain(int idx, int dir) {
        if (idx < 0) return;
        if (data[idx][2] == data[idx + 1][6]) return;
        leftChain(idx - 1, -dir);
        rotate(idx, dir);
    }

    public static void rightChain(int idx, int dir) {
        if (idx > 3) return;
        if (data[idx][6] == data[idx - 1][2]) {
            return;
        }
        rightChain(idx + 1, -dir);
        rotate(idx, dir);
    }

    public static void rotate(int idx, int dir) {
        if (dir == 1) {
            int temp = data[idx][7];
            for (int i = 7; i > 0; i--) {
                data[idx][i] = data[idx][i - 1];
            }
            data[idx][0] = temp;
        } else {
            int temp = data[idx][0];
            for (int i = 0; i < 7; i++) {
                data[idx][i] = data[idx][i + 1];
            }
            data[idx][7] = temp;
        }
    }
}
