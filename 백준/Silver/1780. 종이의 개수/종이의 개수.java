import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        result = new int[3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, n);

        for (int val : result) {
            System.out.println(val);
        }

    }

    public static void divide(int x, int y, int size) {
        if (checkNum(x, y, size)) {
            result[arr[x][y] + 1]++;
        } else {
            int newSize = size / 3;

            divide(x, y, newSize);
            divide(x + newSize, y, newSize);
            divide(x + newSize * 2, y, newSize);
            divide(x, y + newSize, newSize);
            divide(x + newSize, y + newSize, newSize);
            divide(x + newSize * 2, y + newSize, newSize);
            divide(x, y + newSize * 2, newSize);
            divide(x + newSize, y + newSize * 2, newSize);
            divide(x + newSize * 2, y + newSize * 2, newSize);
        }
    }

    public static boolean checkNum(int x, int y, int size) {
        int num = arr[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != num) {
                    return false;
                }
            }
        }
        return true;
    }
}