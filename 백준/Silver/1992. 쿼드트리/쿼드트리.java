import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[][] arr;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        divide(0, 0, n);

        System.out.println(sb);

    }

    public static void divide(int x, int y, int size) {
        if (checkColor(x, y, size)) {
            if (arr[x][y] == 1) {
                sb.append(1);
            } else if (arr[x][y] == 0) {
                sb.append(0);
            }
        } else {
            sb.append('(');
            int newSize = size / 2;

            divide(x, y, newSize); //좌상
            divide(x, y + newSize, newSize); //우상
            divide(x + newSize, y, newSize); //좌하
            divide(x + newSize, y + newSize, newSize); //우하
            sb.append(')');

        }
    }

    public static boolean checkColor(int x, int y, int size) {
        int color = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
